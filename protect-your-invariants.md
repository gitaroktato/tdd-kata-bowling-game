# Protect Your Invariants!

Developers tend to get confused, when they need to find a good place for their business logic. I suspect, that most probably the reasons are related to all those bad examples circulating around the documentations of the most commonly used frameworks and bad habbits coming from coding in old-fashioned enterprise platforms, like J2EE. Most of the time **they're afraid** keeping these important pieces of information in the language elements most relevant to the target domain: I'm talking about the simple classes reflecting the "nouns" of the business logic. Most often you see something like the example below.

```Java
@Entity 
public class ShoppingCart {
    @OneToMany
    private List<LineItems> lineItems;
    // getters and setters
}
```

```Java
@Component
public class ShoppingCartService {
    public void addToCart(ShoppingCart cart, LineItem item) {
        // Logic related to adding a line item to the shopping cart:
        // Preconditions to check, like availability of the item.
        // Operations, like really adding the item to the cart.
        // Postconditions and cleanup, like changing item's availabiltiy and transaction management.
    }
}
```

There's an entity, which is just a placeholder for the database state. It's also reflecting the object-database mapping with some sort of a meta-language, done by various annotations. There is nothing else here, just getters and setters. The real operations are done in another class that's suffixed as a "Service", "Processor" or a "Manager" (these name suffixes are some sort of an anti-pattern by themselves according to the book "Clean Code"). But from the framework's point of view these classes lifecycle are often controlled by the framework itself. Also meaning that it's preferabale not to hold state in the instances of the class: Either because The framework is keeping a single instance per class in some sort of a managed bean (simply as a singleton), or because multiple threads can access the instance's state at the same time, so thread safety is not guaranteed. 

Notice, that I have not talked about anyting related to the target domain. Nothing mentioned about the cart, capacity of the cart, the possibility of putting duplicated items of the cart, etc. But many things were explained related to framework concepts, like thread management, ORM, bean lifecycles and so on. So it's important to mention, that I suspect the framework documentations are intentionally keeping their examples like the one above. They need to minimize the amount code representing the "hard facts" of the domain in the example, because it's irrelevant!

So what's wrong with all of this? Simply the fact, that domain logic is not in focus and it's often scattered around in various places. Stateless operations are managing your domain by some sort of a ["transaction script"](https://www.martinfowler.com/eaaCatalog/transactionScript.html) leading us to an ["anemic domain model"](https://www.martinfowler.com/bliki/AnemicDomainModel.html). There's no place for the real power of Object-Oriented Programming in the form of polymorphism. Nor for really powerful deisgn-patterns, like composites, visitors, strategies, observers, and so on.

Sadly this is especially true if we want to implement some sort of an _invariant_ of the domain. These pieces of code are essential for keeping things together. Essential for keeping or defect density low, by cacthing programming errors early on. The _invariants_ of our domain should be part of a domain layer, unit-tested, and easy to understand. Not making them part of your domain will result more complicated test cases as you try to tailor together all your business logic from different layers or modules of your application. These tests will either run slower or have a complex mocking mechanism and a lot of plumbing code to make the application run without all the clutter. Not ideal if you really aim to understand functional behaivour by reading the test cases.

Should we put these invariants into our application boundaries in some sort of a validation logic? Maybe we could use another [framework or library](https://beanvalidation.org/2.0/) for our aid? I say we should put these implementations very close to our domain instead. Capture them with simple language elments that can be verified with a simple unit test.

![invariants_versus_validation](docs/invariants_vs_validation.png)
_Invariants VS. validation: Often validation is placed on layer boundaries, while invariants are placed at the heart of your software_

## Defensive Programming, Design By Contract
To be honest, when I read about "Design by Contract" in the book ["The Pragmatic Programmer"](https://www.goodreads.com/book/show/4099.The_Pragmatic_Programmer), I got great fan of the subject. Unfortunately, I didn't find good support for it in Java so I just stopped experimenting with it. Later on I just dropped the burden of finding a good library for any kind of work and started implementing invariants and preconditions with simple language elements. Sometimes I formulated these as assertions, sometimes I just used some simplistic functions, but it always kept this kind of code close to the target domain. 

Later, I got familiar with Domain-Driven Design by reading Eric Evans' book. Inside he talked about "Making Implicit Concepts Explicit".That was the point, when I realized that invariants realted to the domain **should** be kept with the target domain and **should** be treated separately from handling possible programmer errors or misformatted input. For instance, a date format is something from the latter while handling important use-cases, like what happens if duplicated line items are put into a shopping cart, are the former.

// TODO DDD book assertions

## Two Examples (Bowling Game and Knight Solver)

OK, now let's look at two practical examples. In the [first one](https://kata-log.rocks/bowling-game-kata) we will implement a score evaluation software for a bowling game. The scoring rules can be explained in a few sentences, so I will just copy them over to here:

### Bowling Rules
#### The Game
_The game consists of 10 frames. In each frame the player has two rolls to knock down 10 pins._
_The score for the frame is the total number of pins knocked down, plus bonuses for strikes and spares._

#### Spares
_A spare is when the player knocks down all 10 pins in two rolls._ 
_The bonus for that frame is the number of pins knocked down by the next roll._

#### Strikes
_A strike is when the player knocks down all 10 pins on his first roll._ 
_The frame is then completed with a single roll. The bonus for that frame is the value of the next two rolls._

#### Tenth frame
_In the tenth frame a player who rolls a spare or strike is allowed to roll the extra balls to complete the frame._
_However, no more than three balls can be rolled in the tenth frame._

For those with poor imagination, here's a bowling scorecard. Spares are shown with black triangles and strikes with rectangles.
![scorecard](docs/bowling_game_sample.png)

The rules seem simplistic, so we can start with something like an int array counting the number of scores. We can factor in the rest of the mentioned rules later (at least we think we can do it relatively easily). 

```java
public class Game {

    private int[] rolls = new int[21];
    private int turn = 0;

    public void roll(int pins) {
        rolls[turn++] = pins;
    }

    public int score() {
        return Arrays.stream(rolls).sum();
    }

}
```

Now let's collect the preconditions and invariants for the `roll()` method. 

- We can't roll more than 10 **pins** at once (physically impossible as there are only 10 pins at maximum on the field)
- Sum of **rolls** can be only 10 in each **frame**

There's a implicit concept which is not mentioned in the code above and it makes the implementation extremely hard to handle, and that's the **frame**. Now let's see preconditions and invariants related to **frames** in our business logic:

- A **game** consists 10 **frames**. (this is captured implicitly with the [magic number](https://refactoring.guru/replace-magic-number-with-symbolic-constant) 21).
- On the tenth **frame** we can have either 2 or 3 **rolls** depending our current score in that **frame**.

How should we represent **frame** in our code? We simply should make it explicit! This has the following positive effect in our implementation:

- Eliminates the hidden SRP violation starting to appear, as we try to do everything in a single `Game` class.
- Immediately eliminates the magic number `21` in our code.
- Helps readabiltiy by explicitly phrasing another "noun" of our doman, called **frame**.
- But most importantly **it's decomposing the problem to smaller subproblems that's easier to solve!**

Here's a diagram, that shows how our class hierarchy should look like.
![class-diagram](docs/class_diagram_openboard.png)





## Conclusion

// TODO making implicit concepts explicit.
// TODO language flaws of the technique, especially inheritance.
// TODO Microservices with shallow domain. Very simple domain.
If you're interested, you can watch me [on YouTube](https://www.youtube.com/watch?v=gxxKPhuw4e8) doing the bowling game implementation step-by-step using TDD. 