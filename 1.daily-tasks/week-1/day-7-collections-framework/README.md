## Collection Timing Comparison

**Test size:** 100,000 elements

| Collection | Append time |
|---|---:|
| ArrayList | 3.867 ms |
| LinkedList | 2.972 ms |

### Observation

In this run, LinkedList append operations took approximately 0.895 ms less than ArrayList. This is only one measurement and does not establish a general performance advantage.

### Collection Choice Justification

- **ArrayList:** Used for the library catalogue because it supports fast indexed access and efficient appending.
- **LinkedHashSet:** Used to remove duplicate book titles while preserving insertion order.
- **ArrayDeque:** Used as a FIFO waitlist, serving requests in arrival order.
- **PriorityQueue:** Used to prioritize faculty requests, then overdue requests, and finally requester names.

### Complexity Notes

- ArrayList: amortized O(1) append; O(1) indexed access.
- LinkedList: O(1) append at the end when maintaining the tail; O(n) indexed access.
- LinkedHashSet: expected O(1) add and lookup.
- ArrayDeque: amortized O(1) insertion and removal at the ends.
- PriorityQueue: O(log n) insertion and removal of the head; O(1) head inspection.

### Limitation

The timing test is a simple demonstration rather than a rigorous benchmark. Results vary with the JVM, computer, and workload.