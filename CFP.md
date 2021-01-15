# Protect your invariants - A practical TDD session

How do you handle constraints and validation inside your application?
Most of the developers still tend to think around model-view-controller and put this logic
somewhere close to their application's boundary. 
Actually, this prevents them from having a rich domain model and ensuring consistency.

In this hands-on TDD session we will go through a practical task and implement it step-by-step.
We're going to divide the problem into smaller pieces and solve them individually, then
put together a working solution with object composition.

I'll show why putting constraints and validation inside your domain is effective and how
you can apply these invariants into an aggregate. 
