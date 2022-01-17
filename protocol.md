# Protocol

During the challenge note down each error/debugging-episode you encounter. An
episode consists out of a single error / symptom you debug. If you notice and start working on another error during an episode, that is a new entry in your protocol. Each protocol entry should consists of the following two lines:
- Time of notice + symptoms of the error
- Explaination of what caused the error + Time of solving


Starting time: 17:36

## 01
- Time: 17:36, Symptoms: Failing Test in foo()
- Explanation: "foo" != "fixme", Time needed: 1 Minute

## 02
- Time: 18:19, Symptoms: Failing Test "checkConjuredItem()"
- Explanation: Logic for conjured item not implemented yet, Time needed: 23 minutes

## 03
- Time: 18:30, Symptoms: Failing Test "checkConjuredItem()"
- Explanation: After sellIn date the conjured item should decrease by 4, it did only decrease by 3, Time Needed: 11 minutes

## 04
- Time: 18:35, Symptoms: Failing Test "checkConjuredItem()"
- Explanation: After hard coding decrease after SellIn Date by one additional quality unit, the quality still decreases only by 3, must be at another part of the code, Time Needed: 6 minutes

## 05
- Time: 18:42, Symptoms: No more Failing Tests
- Explanation: Tested for smaller-equal rather than smaller (i.e. if sell date == 0 we already decrease twice as fast)