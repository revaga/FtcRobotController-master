package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name="heat waves")
public class heatwaves extends LinearOpMode{
    public DcMotor leftFront = null;
    public DcMotor leftRear = null;
    public DcMotor rightFront = null;
    public DcMotor rightRear = null;
    private Servo rightArm = null;
    private Servo leftArm = null;

    public SensorBNO055IMU imu = null;



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
            for (int i = 0; i <= 6; i++) {
                //wait 17 seconds for the singing
                //sleep(17000);
                //spin
                leftSpin(4000);
                rightSpin(4000);
                leftArm.setPosition(2.3);
                rightArm.setPosition(2.3);
                leftArm.setPosition(0.0);
                rightArm.setPosition(0.0);
                forward(24);
                //move backwards
                backward(14);

                //left arm up
                leftArm.setPosition(0.0);
                //right arm up
                rightArm.setPosition(0.0);
                rightSpin(2000);
                //until 1:03
            }

            //spin with arms up till
            forward( 24);
            backward(24);
            forward(24);
            backward(24);
            leftSpin(2000);
            rightSpin(2000);
            //until 1:30
            leftArm.setPosition(2.3);
            rightArm.setPosition(2.3);
            leftArm.setPosition(0.0);
            rightArm.setPosition(0.0);
            forward(44);
            //move backwards
            backward(34);
            //repeat the chorus moves until 1:40
            rightArm.setPosition(2.3);
            leftArm.setPosition(2.3);
            rightSpin(4000);
            leftSpin(4000);
            //spin until 1:58
            for (int i = 0; i <= 2; i++) {
                rightArm.setPosition(0);
                leftArm.setPosition(0);
                sleep(5000);
                //until 2:03
                leftArm.setPosition(1.5);
                rightArm.setPosition(1.5);
                shuffleLeft(24);
                forward(24);
                backward(24);

            }
            //spin
            leftSpin(2000);
            //shuffle right
            rightSpin(2000);
            shuffleLeft(30);
            shuffleRight(12);
            //until 2:28
            for (int i = 0; i <= 2; i++) {
                rightArm.setPosition(0.0);
                leftArm.setPosition(0.0);
                rightArm.setPosition(2.3);
                leftArm.setPosition(2.3);
                rightArm.setPosition(0.0);
                leftArm.setPosition(0.0);
                rightArm.setPosition(2.3);
                leftArm.setPosition(2.3);
                rightArm.setPosition(0.0);
                leftArm.setPosition(0.0);
                rightArm.setPosition(2.3);
                leftArm.setPosition(2.3);
                //arms up and down until 2:52
                sleep(2500);
            }
            rightArm.setPosition(0.0);
            leftArm.setPosition(0.0);
            // shuffle left and right until 1:50
            for(int i = 0; i<=4; i++){
                leftSpin(4000);
                rightSpin(4000);
                shuffleLeft(24);
                shuffleRight(24);
                forward(40);
                backward(40);
                leftSpin(4000);
                rightSpin(4000);
                forward(40);
                backward(40);
                //chorus moves until 3:19
            }
            for(int i = 0; i<=2; i++) {
                //spin left
                rightArm.setPosition(1.5);
                leftArm.setPosition(1.5);
                //spin right
                leftSpin(1000);
                rightSpin(1000);
                //until 3:31
                forward(30);
                backward(30);
                forward(30);
                backward(30);
                rightSpin(5000);
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
    public void getCurrent() {

    }
    private void rightSpin(int x) {
        leftFront.setTargetPosition(leftFront.getCurrentPosition() + x);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() - x);
        rightRear.setTargetPosition(rightRear.getCurrentPosition() - x);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() + x);

        run();

        setPower(1);

    }
    private void leftSpin(int x) {
        leftFront.setTargetPosition(leftFront.getCurrentPosition() - x);
        rightFront.setTargetPosition(rightFront.getCurrentPosition() + x);
        rightRear.setTargetPosition(rightRear.getCurrentPosition() + x);
        leftRear.setTargetPosition(leftRear.getCurrentPosition() - x);

        run();

        setPower(1);
    }

}

