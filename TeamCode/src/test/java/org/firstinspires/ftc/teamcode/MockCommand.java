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

import android.os.AsyncTask;
import opmodes.LinearOpMode;
import org.firstinspires.ftc.teamcode.commands.Command;

public class MockCommand extends Command {

  public volatile boolean ran = false;
  public volatile boolean fail = false;

  public MockCommand() {
    super(new MockOpMode());
  }

  public MockCommand(LinearOpMode opMode) {
    super(opMode);
  }

  public MockCommand(boolean fail) {
    super(new MockOpMode());
    this.fail = fail;
  }

  public MockCommand(LinearOpMode opMode, boolean fail) {
    super(opMode);
    this.fail = fail;
  }

  @Override
  public boolean execute(AsyncTask commandThread) {
    System.out.println("Testing");
    ran = true;
    isFinished = true; // onPostExecute never run in tests
    return !fail;
  }

  public void unthreadedExecute() {
    new CommandThreadTest().unthreadedExecute(this);
  }

  private class CommandThreadTest extends CommandThread {

    public void unthreadedExecute(Command command) {
      boolean result = doInBackground(new Command[]{command});
      onPostExecute(result);
    }
  }
}