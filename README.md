## WIP

# Stability deployment for syndesis long-running test

## Architecture
 - Syndesis/Fuse online installation

 - AMQ broker

 - Number creator

 - Number guesser
 
 ## used Syndesis components
 - AMQ - connection to amq broker deployed on openshift
 - HTTP (number-creator) - rest service for generating numbers 
 - HTTP(number-guesser) - Guess numbers 
 - delay, log steps
 - advanced filter

## Integrations

 - _Create new number_  - From AMQ('new-number') queue -> HTTP(number-guesser/reset) -> HTTP(number-creator/create) ->'first-number' queue

 - Fist Guess - From AMQ('first-number') queue to HTTP(number-guesser/guess) to  AMQ('guesses') queue

 - Provide feedback - From  AMQ('guessed') to HTTP(number-creator/compare) delay -> to AMQ('have-number') -> filter (${bodyAs(String)} contains 'number') -> log -> stats/AMQ('guessed numbers')

 - Process feedback - From  AMQ('have-number') -> (${bodyAs(String)} not contains 'number') -> HTTP('number-quesser/update') -> HTTP('number-quesser/guess') -> AMQ ('guesses') queue' ->AMQ ('guess-statistic') queue'

 - Guess stats - AMQ (guess-stats) -> LOG -> LOG

 - Guessed stats - AMQ('guessed numbers') -> Log -> AMQ(new-number)
