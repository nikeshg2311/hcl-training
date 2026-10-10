# Day 6 — Exception Handling and AI-Assisted Debugging

## 1. Concepts Practiced

- Checked exceptions and custom exception classes
- `try`, `catch`, and `finally`
- `throw` and `throws`
- Exception chaining using the original cause
- Reading Java stack traces
- AI-assisted debugging and manual verification

## 2. Order Processor

Implemented an order processor that validates requested quantities against available stock.

### Test results

- Order for 4 items succeeded.
- Order for 20 items failed because stock was insufficient.
- Order for 3 items succeeded after the failed order.
- Final available stock was 3 items.

The failed order did not reduce the available stock.

## 3. Custom Exceptions

- `InsufficientStockException`: A checked exception for insufficient inventory.
- `OrderProcessingException`: Wraps an order-processing failure while preserving the original cause.

## 4. Stack Trace Analysis

The stack trace showed `OrderProcessingException` as the main exception.

Its cause was `InsufficientStockException`, which identified the actual business problem: 50 items were requested when only 3 were available.

The stack trace identified the methods involved:

- `OrderProcessor.processOrder()`
- `OrderProcessor.processOrderWithAudit()`
- `OrderProcessorApp.main()`

## 5. AI-Assisted Debugging

AI explanation: The outer exception reports the high-level failure, while the `Caused by` section preserves the underlying exception.

Manual verification: Checked the stack trace and source code to confirm that `processOrder()` throws `InsufficientStockException` and `processOrderWithAudit()` wraps it using `new OrderProcessingException(message, e)`.

Conclusion: The AI explanation matches the observed output and implementation.

## 6. Evidence

- `evidence/exception-chaining-stack-trace.png`

## 7. Completion Status

Order processing, custom exception handling, exception chaining, and stack-trace analysis have been tested successfully.