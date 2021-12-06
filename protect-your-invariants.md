# Protect Your Invariants!

Developers tend to get confused, when they need to find a good place for their business logic. I suspect, that most probably the reasons are related to all those bad examples circulating around the documentations of the most commonly used frameworks and bad habbits coming from coding old-fashioned enterprise platforms, like J2EE. Most of the time **they're afraid** keeping these important pieces of information in the language elements most relevant to the target domain: I'm talking about the simple classes reflecting the "nouns" of the business logic. Most often you see sometghing like the example below instead.

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

There's an entity, which is just a placeholder for the database state. It's also reflecting the object-database mapping with some sort of a meta-language, done by various annotations. There is nothing else here, just getters and setters. The real operations are done in another class that's suffixed as a "Service", "Processor" or a "Manager" (these name suffixes are some sort of an anti-pattern by themselves according to the book "Clean Code"). But from the framework's point of view these classes lifecycle are often controlled by the framework itself. Also meaning that it's preferabale not to hold state in the instances of the class: Either because The framework is keeping a single instance per class in some sort of a managed bean (simply as a singleton), or because multiple threads can access the instance's state at the same time so thread safety is not guaranteed. 

Notice, that I have not talked about anyting related to the target domain. Nothing mentioned about the cart, capacity of the cart, the possibility of putting duplicated items of the cart etc. But many things were explained related to the framework, like thread management, ORM, bean lifecycles and so on. So it's important to mention, that I suspect the framework documentations are intentionally keeping their examples like the one above. They need to minimize the amount code representing the "hard facts" of the domain in the example, because it's irrelevant!

So what's wrong with all of this? Simply the fact, that domain logic is not in focus and it's often scattered around in various places. Stateless operations are managing state by some sort of a ["transaction script"](https://www.martinfowler.com/eaaCatalog/transactionScript.html) leading us to an ["anemic domain model"](https://www.martinfowler.com/bliki/AnemicDomainModel.html). There's no place for the real power of Object-Oriented Programming in the form of polymorphism. Nor for really powerful deisgn-patterns, like composites, visitors, strategies, observers, and so on.

Sadly this is especially true if the logic in scope is related to some sort of an invariant of the domain. These pieces of code are essential for keeping things together. Essential for keeping or defect density low, by cacthing logic errors early on. The invariants of our domain should be part of a domain layer, well unit tested, and easy to understand.  Not making them part of your domain will result as // TODO hard to verify, etc.

## Defensive programming, design-by-contract

## Two examples (Bowling Game and Knight Solver)

## Conclusion