# How to use the OpModes folder

## What are OpModes?
OpModes are the programs that the robot runs, like an autonomous program or TeleOp.

## How to name OpModes
OpModes are named with a prefix, an underscore, and a name in the form <prefix>_<name>.

### Prefixes
There are only four valid prefixes.
 - Auto: This prefix indicates an autonomous program.
 - TeleOp: This prefix indicates a driver controller program.
 - Test: This prefix indicates that the program isn't really meant for normal use and is a testing program only. For
   example, a program that prints out sensor readings would have a Test prefix.
 - Util: This prefix indicates that the program can be used in a competition  but not on the field. For example, an
   OpMode that calibrated a sensor would have a Util prefix.
Note that these prefixes also double as the group in the TeleOp and Autonomous annotations.

### Names
Names should describe the OpMode as well as possible without being too long. If it is multiple words, they should be
combined with /(Upper)?CamelCase|PascalCase/ to remove spaces.

## What about LinearOpMode?
This class isn't really an OpMode in the traditional sense. Instead, some OpModes extend it, so it belongs in this
folder but does not have to follow these naming rules.
