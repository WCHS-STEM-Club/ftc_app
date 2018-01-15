/*
* MIT License

Copyright (c) 2018 Nathan Varner

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package org.firstinspires.ftc.teamcode;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.firstinspires.ftc.teamcode.commands.Command;
import org.junit.Test;

public class CommandTest {

  /**
   * Ensure that construction works properly
   */
  @Test
  public void construction() {
    Command command = new MockCommand();

    assertFalse("Did not set isFinished to false.", command.isFinished);
    assertFalse("Did not set success to false.", command.success);
  }

  /**
   * Ensure that the command runs properly
   */
  @Test
  public void run() {

    MockCommand command = new MockCommand();

    command.unthreadedExecute();

    assertTrue("Did not set ran to true", command.ran);
    assertTrue("Did not set isFinished to true", command.isFinished);
  }

  /**
   * Ensure that failure results in proper variable states
   */
  @Test
  public void fail() {
    MockCommand command = new MockCommand(true);

    command.unthreadedExecute();

    assertTrue("Did not set isFinished to true", command.isFinished);
    assertFalse("Did not set success to false", command.success);
  }
}
