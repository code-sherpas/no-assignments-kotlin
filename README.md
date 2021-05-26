## Anatomy of a scope function:
1. Accepts a lambda expression and evaluates to it

inline fun <T, R> T.let(block: (T) -> R): R

2. Accepts a block and executes on a receiver
   inline fun <T, R> with(receiver: T, block: T.() -> R): R

3. Accepts a block of code and evaluates to Context object

inline fun T.apply(block: T.() -> Unit): T

The difference between scope functions is how the object become available inside the block (this or it)

## Benefits

Scope functions help us make our code more concise and readable

## How to choose what scope function is best for your use case:

Function	Object reference	Return value	is extension function
let	it	Lambda result	Yes
run	this	Lambda result	Yes
run	-	Lambda result	No: called without the context object
with	this	Lambda result	No: takes the context object as an argument
apply	this	Context object	Yes
also	it	Context object	Yes