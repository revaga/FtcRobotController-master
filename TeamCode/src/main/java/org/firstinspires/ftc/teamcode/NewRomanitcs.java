package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name="New Romantics")
public class NewRomanitcs extends LinearOpMode{
    public DcMotor leftFront = null;
    public DcMotor leftRear = null;
    public DcMotor rightFront = null;
    public DcMotor rightRear = null;
    private Servo rightArm = null;
    private Servo leftArm = null;


    @Override
    public void runOpMode() throws InterruptedException{
        final int COUNT_PER_MOTOR_REV = 1120;
        leftFront = hardwareMap.dcMotor.get("lf");
        leftRear = hardwareMap.dcMotor.get("rf");
        rightFront = hardwareMap.dcMotor.get("lb");
        rightRear = hardwareMap.dcMotor.get("rb");

        leftArm = hardwareMap.servo.get("al");
        rightArm = hardwareMap.servo.get("ar");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftArm.setPosition(0.0);
        rightArm.setPosition(0.0);

        waitForStart();
        while (opModeIsActive()) {
            //new romantics
            for (int i = 0; i <= 6; i++) {
                //left arm up
                leftArm.setPosition(1.5);
                //right arm down
                rightArm.setPosition(1.5);
                //left arm down
                sleep(1000);
                leftArm.setPosition(0.0);
                //right arm up
                rightArm.setPosition(0.0);
                sleep(1000);
                //until 0:47
            }

            //spin with arms up till 0:52
            rightArm.setPosition(0.0);
            leftArm.setPosition(1.5);
            sleep(50);
            rightArm.setPosition(2.3);
            leftArm.setPosition(1.5);
            leftSpin(2000);
            //spin other way with arms up till 1:04
            rightSpin(2000);
            //both up and down 5 times
            for (int i = 0; i <= 2; i++) {
                rightArm.setPosition(0.0);
                leftArm.setPosition(1.5);
                sleep(750);
                rightArm.setPosition(2.3);
                leftArm.setPosition(1.5);
                sleep(750);
            }
            //shuffle left
            shuffleLeft(24);
            //shuffle right
            shuffleRight(36);
            rightSpin(2000);
            sleep(400);
            shuffleRight(24);
            // alternating arms up and down until 1:35
            for (int i = 0; i <= 2; i++) {
                rightArm.setPosition(0.0);
                leftArm.setPosition(2.3);
                sleep(750);
                rightArm.setPosition(2.3);
                leftArm.setPosition(0.0);
                sleep(750);
                rightSpin(800);
            }
            rightArm.setPosition(0.0);
            leftArm.setPosition(0.0);
            // shuffle left and right until 1:50
            for(int i = 0; i<=4; i++){
                shuffleLeft(24);
                shuffleRight(24);
            }
            for(int i = 0; i<=2; i++) {
                //spin left
                leftSpin(750);
                //spin right
                rightSpin(750);
                //alternate arms up and down but faster
                rightArm.setPosition(0.0);
                leftArm.setPosition(2.3);
                sleep(50);
                rightArm.setPosition(2.3);
                leftArm.setPosition(0.0);
                sleep(50);
                forward(36);
                //shuffle left with arms in the middle
                backward(36);
            }
            //repeat the last 3 steps until 2:08
            //shuffle right with arms in the middle
            leftArm.setPosition(1.1);
            rightArm.setPosition(1.1);
            for(int i = 0; i<=2; i++) {
                shuffleRight(20);
                //shuffle left with arms in the middle
                shuffleLeft(20);
                //spin left with arms up
                leftSpin(500);
            }
            // repeat last 3 steps till 2:27
            // both arms up and down until 2:40
            for (int i = 0; i <= 2; i++) {
                rightArm.setPosition(0.0);
                leftArm.setPosition(0.0);
                sleep(750);
                rightArm.setPosition(2.3);
                leftArm.setPosition(2.3);
                sleep(750);
                leftSpin(500);
                forward(36);
                //shuffle left with arms in the middle
                backward(36);
            }
            //shuffle left and right until 2:55
            for(int i = 0; i<=2; i++) {
                shuffleRight(20);
                //shuffle left with arms in the middle
                shuffleLeft(20);
                forward(36);
                //shuffle left with arms in the middle
                backward(36);
            }

            //turn left and right until 3:28
            for(int i = 0; i<=2; i++) {
                rightSpin(600);
                //shuffle left with arms in the middle
                leftSpin(600);
                forward(36);
                //shuffle left with arms in the middle
                backward(36);
            }
            //move front and back until the end
            for(int i = 0; i<=2; i++) {
                forward(36);
                //shuffle left with arms in the middle
                backward(36);

            }
        }

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

        setPower(1);

        run();

    }
    private void backward(int x) {
        leftFront.setTargetPosition(leftFront.getCurrentPosition() - x);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() - x);
        rightRear.setTargetPosition(rightRear.getCurrentPosition() - x);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() - x);

        run();

        setPower(1);

    }

    private void leftTurn(int x) {
        leftFront.setTargetPosition(leftFront.getCurrentPosition() - x);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + x);
        rightRear.setTargetPosition(rightRear.getCurrentPosition() + x);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() - x);

        run();

        setPower(1);
    }
    private void rightTurn(int x) {
        leftFront.setTargetPosition(leftFront.getCurrentPosition() + x);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() - x);
        rightRear.setTargetPosition(rightRear.getCurrentPosition() - x);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() + x);

        run();

        setPower(1);

    }
    private void shuffleRight( int x){

        leftFront.setTargetPosition(leftFront.getCurrentPosition() - x);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + x);
        rightRear.setTargetPosition(rightRear.getCurrentPosition() - x);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() + x);

        run();

        setPower(1);
    }

    private void shuffleLeft (int x){
        leftFront.setTargetPosition(leftFront.getCurrentPosition() + x);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() - x);
        rightRear.setTargetPosition(rightRear.getCurrentPosition() + x);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() - x);

        run();

        setPower(1);

    }
    private void rightSpin(int x) {
        leftFront.setTargetPosition(leftFront.getCurrentPosition() + x);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() - x);
        rightRear.setTargetPosition(rightRear.getCurrentPosition() - x);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() + x);

        run();

        setPower(5);

    }
    private void leftSpin(int x) {
        leftFront.setTargetPosition(leftFront.getCurrentPosition() - x);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + x);
        rightRear.setTargetPosition(rightRear.getCurrentPosition() + x);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() - x);

        run();

        setPower(5);
    }

}
