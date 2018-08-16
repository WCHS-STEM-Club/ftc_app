/*
MIT License

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

package com.nathanvarner.pid;

public class PID {
  // Weights
  private double m_kp;
  private double m_ki;
  private double m_kd;

  // Setpoint
  private double m_setpoint;

  // Save some things for I and D
  private double m_integral;
  private double m_lastError;
  private long m_lastTime;

  /**
   * @param kp The proportional weight, obtained through tuning
   * @param ki The integral weight, obtained through tuning
   * @param kd The derivative weight, obtained through tuning
   * @param setpoint The target for the measured value
   */
  public PID(double kp, double ki, double kd, double setpoint) {
    m_kp = kp;
    m_ki = ki;
    m_kd = kd;
    m_setpoint = setpoint;
    m_lastTime = PID.now();
    m_integral = 0;
    m_lastError = 0;
  }

  /**
   * Method to get the current time in ms
   * @return The current time in ms
   */
  private static long now() {
    return System.currentTimeMillis();
  }

  /**
   * Calculates the PID controller's output for this iteration.
   * @param measuredValue The value measured this iteration
   * @return The PID controller's output
   */
  public double pid(double measuredValue) {
    double error = m_setpoint - measuredValue;
    long now = PID.now();
    long deltaTime = now - m_lastTime;
    if (deltaTime == 0) deltaTime++;
    m_lastTime = now;
    return p(error) + i(error, deltaTime) + d(error, deltaTime);
  }

  /**
   * Calculate the proportional component of the PID controller based on the error. The proportional component is
   * the error or difference between the setpoint and measured value. Its contribution to the controller is to do
   * most of the "work" of minimizing the error. The proportional component alone would minimize the error very fast,
   * but because of momentum would end up overshooting and oscillating. The PID controller is designed to prevent
   * this oscillation.
   * @param error The difference between the setpoint and measured value
   * @return The proportional component
   */
  public double p(double error) {
    return error * m_kp;
  }

  /**
   * Calculate the integral component of the PID controller based on the error and delta time. The integral component
   * is the integral of a graph of the error, or the sum of all errors times the time since last iteration. Its
   * contribution to the controller is to reduce steady state error, where the measured value is just above or below
   * the setpoint and is changing slowly. The integral grows quickly as the small error adds up with time to
   * counteract this steady state error.
   * @param error The difference between the setpoint and measured value
   * @param deltaTime The difference between this iteration's time and last iteration's time
   * @return The integral component
   */
  public double i(double error, long deltaTime) {
    m_integral += error * deltaTime;
    return m_ki * m_integral;
  }

  /**
   * Calculate the derivative component of the PID controller based on the error and delta time.  The derivative
   * component is the derivative of the error. Its contribution to the controller is to prevent overshoot by trying
   * to flatten out the error graph. It can "predict" the oscillations to stop or at least shorten them.
   * @param error The difference between the setpoint and measured value
   * @param deltaTime The difference between this iteration's time and last iteration's time
   * @return The derivative component
   */
  public double d(double error, long deltaTime) {
    double deltaError = error - m_lastError;
    m_lastError = error;
    double derivative = deltaError / deltaTime;
    return m_kd * derivative;
  }

  public void setSetpoint(double newSetpoint) {
    m_setpoint = newSetpoint;
  }
}
