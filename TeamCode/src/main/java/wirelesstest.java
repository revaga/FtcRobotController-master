package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

//import com.qualcomm.hardware.stmicroelectronics.VL53L0X;

    @Autonomous (name="Blue Auto")
    public class wirelesstest extends LinearOpMode {
        public DcMotor leftFront = null;
        public DcMotor rightFront = null;
        public DcMotor leftRear = null;
        public DcMotor rightRear = null;
        public DcMotor arm = null;
        double armPower;
        public DcMotor spinnyMotor = null;

        private Servo claw = null;
        private Servo levelArm = null;

        private ColorSensor sensorColor = null;
        private DistanceSensor sensorDistance = null;


        @Override
        public void runOpMode() throws InterruptedException {
            final int COUNTS_PER_MOTOR_REV = 1120;
            leftFront = hardwareMap.dcMotor.get("fleft");
            leftRear = hardwareMap.dcMotor.get("fright");
            rightFront = hardwareMap.dcMotor.get("bleft");
            rightRear = hardwareMap.dcMotor.get("bright");
            arm = hardwareMap.dcMotor.get("arm");

            sensorColor = hardwareMap.get(ColorSensor.class, "sensor");
            sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor");

            claw = hardwareMap.servo.get("claw");
            levelArm = hardwareMap.servo.get("level");

            spinnyMotor = hardwareMap.dcMotor.get("spin");

            leftFront.setDirection(DcMotor.Direction.REVERSE);
            leftRear.setDirection(DcMotor.Direction.REVERSE);

            leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            spinnyMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            claw.setPosition(0.8);

            waitForStart();
            while (opModeIsActive()) {
                claw.setPosition(0.8);

                //carousel works 1/17
                shuffleright(num(3));
                backward(num(20));
                spinWheel(num(13));

                forward(num(2));
                rightTurn(num(18));
                forward(num(10));
                leftTurn(num(18));
                forward(num(14));

                int level = 0;
                if (sensorDistance.getDistance(DistanceUnit.INCH) < 40) {
                    level = 1;
                }
                forward(num(8));
                if (level == 1) {
                    armUp(level);
                    forward(num(30));
                    telemetry.addData("Level: ", level);
                    telemetry.update();
                }
                else if (sensorDistance.getDistance(DistanceUnit.INCH) < 40) {
                    level = 2;
                    armUp(level);
                    forward(num(26));
                    telemetry.addData("Level: ", level);
                    telemetry.update();
                } else {
                    level = 3;
                    armUp(level);
                    forward(num(26));
                    telemetry.addData("Level: ", level);
                    telemetry.update();
                }
                rightTurn(num(18));
                if (level == 3) {
                    forward(num(14));
                    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    arm.setPower(armPower);
                    while (opModeIsActive() && arm.isBusy()) {
                    }
                }
                else
                    forward(num(16));
                claw.setPosition(0.6);
                backward(num(3));
                if (level==3){
                    arm.setTargetPosition(arm.getCurrentPosition()+50);
                    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    arm.setPower(armPower);
                    while (opModeIsActive() && arm.isBusy()) {
                    }
                }
                levelArm.setPosition(0.2);
                leftTurn(num(18));
                shuffleleft(num(6));
                forward(num(76));
                stop();

         /*
         for (int i = 0; !isFound && i < 2; i++) {
             if (sensorDistance.getDistance(DistanceUnit.INCH) < 3) {
                 isFound = true;
                 level++;
                 pick(level);
             } else {
                 if (!isFound)
                     level++;
                 forward(num(3));
             }
         }
          */

                //      telemetry.addData("level: ", level);
                //  telemetry.update();

         /*
         if (!isFound){
             pick(level);
         }

         stop();
         telemetry.addData("range", String.format("%.01f in", sensorDistance.getDistance(DistanceUnit.INCH)));
         telemetry.update();

          */
            }
        }
        public int num (float inches_to_move) {
            float num_inches_per_rev = 3*(float)Math.PI;
            float counts_to_move = (inches_to_move/num_inches_per_rev)*1120;
            int rounded_counts_to_move = Math.round(counts_to_move);
            return (rounded_counts_to_move);
        }

        private void setPower(double power) {
            leftFront.setPower(power);
            leftRear.setPower(power);
            rightFront.setPower(power);
            rightRear.setPower(power);
        }

        public void run(){
            leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        private void forward(int x) {
            leftFront.setTargetPosition(leftFront.getCurrentPosition() + x);
            rightFront.setTargetPosition(rightFront.getCurrentPosition() + x);
            rightRear.setTargetPosition(rightRear.getCurrentPosition() + x);
            leftRear.setTargetPosition(leftRear.getCurrentPosition() + x);

            run();

            setPower(1);

            while (opModeIsActive() && leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            }
            setPower(0);

        }

        private void backward(int x) {
            leftFront.setTargetPosition(leftFront.getCurrentPosition() - x);
            rightFront.setTargetPosition(rightFront.getCurrentPosition() - x);
            rightRear.setTargetPosition(rightRear.getCurrentPosition() - x);
            leftRear.setTargetPosition(leftRear.getCurrentPosition() - x);

            run();

            setPower(1);

            while (opModeIsActive() && leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            }
            setPower(0);
        }

        private void leftTurn(int x) {
            leftFront.setTargetPosition(leftFront.getCurrentPosition() - x);
            rightFront.setTargetPosition(rightFront.getCurrentPosition() + x);
            rightRear.setTargetPosition(rightRear.getCurrentPosition() + x);
            leftRear.setTargetPosition(leftRear.getCurrentPosition() - x);

            run();

            setPower(1);

            while (opModeIsActive() && leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            }
            setPower(0);
        }
        private void rightTurn(int x) {
            leftFront.setTargetPosition(leftFront.getCurrentPosition() + x);
            rightFront.setTargetPosition(rightFront.getCurrentPosition() - x);
            rightRear.setTargetPosition(rightRear.getCurrentPosition() - x);
            leftRear.setTargetPosition(leftRear.getCurrentPosition() + x);

            run();

            setPower(1);

            while (opModeIsActive() && leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            }
            setPower(0);
        }
        private void shuffleright ( int x){

            leftFront.setTargetPosition(leftFront.getCurrentPosition() - (x));
            rightFront.setTargetPosition(rightFront.getCurrentPosition() + x);
            rightRear.setTargetPosition(rightRear.getCurrentPosition() - x);
            leftRear.setTargetPosition(leftRear.getCurrentPosition() + x);

            run();

            setPower(1);

            while (opModeIsActive() && leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            }
            setPower(0);
        }

        private void shuffleleft (int x) {
            leftFront.setTargetPosition(leftFront.getCurrentPosition() + x);
            rightFront.setTargetPosition(rightFront.getCurrentPosition() - x);
            rightRear.setTargetPosition(rightRear.getCurrentPosition() + x);
            leftRear.setTargetPosition(leftRear.getCurrentPosition() - x);

            run();

            setPower(1);

            while (opModeIsActive() && leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            }
            setPower(0);
        }

        private void spinWheel(int x){
            spinnyMotor.setTargetPosition(spinnyMotor.getCurrentPosition() + x);
            spinnyMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            spinnyMotor.setPower(1);
            while (opModeIsActive() && spinnyMotor.isBusy()){
            }
            spinnyMotor.setPower(0);
        }
        public void pick(int level){
            claw.setPosition(0.6);
            levelArm.setPosition(0.52);
       /*forward(num(3));
       claw.setPosition(0);
       if (level==1)
           forward(num(24));
       else if (level==2)
           forward(num(21));
       else if (level==3)
           forward(num(18));
       claw.setPosition(0.4);
       armUp(level);
       shuffleright(num(6));
       forward(num(6));
       claw.setPosition(0);
       backward(num(6));
       armDown(level);
       claw.setPosition(0.4);*/

        }

        public void armDown(int level$){
            armPower = 0.9;
            int position = arm.getCurrentPosition();
            telemetry.addData("Encoder Position", position);
            if (level$== 1)
                arm.setTargetPosition(arm.getCurrentPosition());
            else if (level$ == 2)
                arm.setTargetPosition(arm.getCurrentPosition() - 30);
            else if (level$ == 3)
                arm.setTargetPosition(arm.getCurrentPosition() - 40);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(armPower);
            while (opModeIsActive() && arm.isBusy()) {
            }
            setPower(0);
        }

        public void armUp(int level){
            armPower = 1;
            int position = arm.getCurrentPosition();
            telemetry.addData("Encoder Position", position);
            if (level == 1) {
                levelArm.setPosition(0.4);
                telemetry.addData("level", level);
            }
            else if (level == 2) {
                levelArm.setPosition(0.28);


                telemetry.addData("level", level);
            }
            else if (level == 3) {
                levelArm.setPosition(0.38);
                arm.setTargetPosition(arm.getCurrentPosition() - 50);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm.setPower(armPower);
                while (opModeIsActive() && arm.isBusy()) {
                }

                telemetry.addData("level", level);
            }

            setPower(0);
            sleep(2000);
        }

        public ColorSensor getSensorColor() {
            return sensorColor;
        }
    }
