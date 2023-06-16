# Sequential Function Mod

## a mod to add a command which can execute function with time sequence in Minecraft

### Usage

the command "seqfunction" is a modified version of "function".

    /seqfunction <name of function>

the command "delay" is to delay a game tick when the mcfunction is being executed by seqfunction.

### Example

    #example.mcfunction
    say tick 0
    delay
    say tick 1
    delay
    say tick 2
    delay
    say tick 3
    delay
    say tick 4
    delay
    say tick 5

use "/seqfunction example" to execute.
