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

## Defensive Programming, Design By Contract
To be honest, when I read about "Design by Contract" in the book ["The Pragmatic Programmer"](https://www.goodreads.com/book/show/4099.The_Pragmatic_Programmer), I got great fan of the subject. Unfortunately, I didn't find good support for it in Java so I just stopped experimenting with it. Later on I just dropped the burden of finding a good library for any kind of work and started implemneting invariants and preconditions with simple language elements. Sometimes I formulated these as assertions, sometimes I just used some simplistic functions, but it always kept this kind of code close to the target domain. 

Later, I got familiar with Domain-Driven Design by reading Eric Evans' book. Inside he talked about "Making Implicit Concepts Explicit".That was the point, when I realized that invariants realted to the domain **should** be kept with the target domain and **should** be treated separately from handling possible programmer errors or misformatted input. For instance, a date format is something from the latter while handling important use-cases, like what happens if duplicated line items are put into a shopping cart, are the former.

// TODO DDD book assertions
// TODO DBC libraries now

## Two Examples (Bowling Game and Knight Solver)



## Conclusion

... // TODO making implicit concepts explicit.
// TODO language flaws of the technique, especially inheritance.

.. Microservices with shallow domain. Very simple domain.