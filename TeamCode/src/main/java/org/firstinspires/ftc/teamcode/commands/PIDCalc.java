package org.firstinspires.ftc.teamcode.commands;

/**
 * Calculate components of PID
 */
public class PIDCalc {

  private static final double TAU = Math.PI * 2;

  private double lastError = 0;
  private double lastI = 0;

  public double calcError(double currentValue, double target) {
    return target - currentValue;
  }

  /**
   * Calculate the proportional
   *
   * @param error The error calculated by {@link PIDCalc#calcError(double, double)}.
   * @param gain The gain
   * @return The proportional correction
   */
  public double calcP(double error, double gain) {
    return error * gain;
  }

  /**
   * Calculate the integral
   *
   * @param error The error calculated by {@link PIDCalc#calcError(double, double)}.
   * @param gain The gain
   * @return The integral correction
   */
  public double calcI(double error, double gain) {
    double gainOverTau = gain / TAU;
    double currentI = lastI + gainOverTau * error;
    lastI = currentI; // Save it for next time
    return currentI;
  }

  /**
   * Calculate the derivative
   *
   * @param error The error calculated by {@link PIDCalc#calcError(double, double)}.
   * @param gain The gain
   * @return The derivative correction
   */
  public double calcD(double error, double gain) {
    double gainOverTau = gain / TAU;
    double deltaError = error - lastError;
    lastError = error; // Save it for next time 
    return gainOverTau * deltaError;
  }

  /**
   * This function can calculate one step of a PID controller. The gains should be tuned properly.
   *
   * @param currentValue The current value of the number
   * @param target The target value of the number
   * @param gainP The P gain
   * @param gainI The I gain
   * @param gainD The D gain
   * @return The PID correction
   */
  public double calcPid(double currentValue, double target, double gainP, double gainI,
      double gainD) {
    double error = calcError(currentValue, target);
    return calcP(error, gainP) + calcI(error, gainI) + calcD(error, gainD);
  }
}
