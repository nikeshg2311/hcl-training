## 4. Code Review Findings — Verified by Testing

| No. | Finding | Evidence from testing | Fix or improvement |
|---|---|---|---|
| 1 | The constructor accepts negative student IDs. | ID `-1` was accepted. | Validate the ID in the constructor. |
| 2 | The constructor accepts blank names. | A name containing spaces was accepted. | Reject null and blank names. |
| 3 | The constructor accepts negative ages. | Age `-5` was accepted. | Require a positive age. |
| 4 | The constructor accepts grades above 100. | Grade `150.0` was accepted. | Enforce the 0–100 range. |
| 5 | Setters accept invalid values. | Blank name, age `-2`, and grade `-10.0` were accepted. | Apply the same validation in setters. |

### Additional Observation

`StudentService` correctly rejected the student with grade `150.0` and displayed the message `Student grade must be between 0 and 100.` This demonstrates that the service validates student data before storing it.

### Verification Status

The five findings above were reproduced by running `StudentReviewApp`. The behavior was confirmed, but the proposed entity-level fixes have not yet been implemented or retested.
