package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name = "let it go ")
public class letitgo extends LinearOpMode {
    private DcMotor right_front = null;
    private DcMotor right_back = null;
    private DcMotor left_front = null;
    private DcMotor left_back = null;

    private Servo arm_left = null;
    private Servo arm_right = null;


    @Override
    public void runOpMode() throws InterruptedException {
        right_front = hardwareMap.dcMotor.get("rf");
        right_back = hardwareMap.dcMotor.get("rb");
        left_front = hardwareMap.dcMotor.get("lf");
        left_back = hardwareMap.dcMotor.get("lb");

        right_front.setDirection(DcMotor.Direction.REVERSE);
        right_back.setDirection(DcMotor.Direction.REVERSE);

        arm_left = hardwareMap.servo.get("al");
        arm_right = hardwareMap.servo.get("ar");
        arm_left.setPosition(0.7); //left arm parallel to ground facing forward
        arm_right.setPosition(0.33); //right arm parallel to ground facing forward

        waitForStart();
        while (opModeIsActive()) {

            forward(num(49), 0.6);
            backward(num(51), 0.6);
            forward(num(47), 0.6);
            backward(num(45), 0.6);
            turnRight(num(10)); //90˚ counter clockwise
            forward(num(15), 0.5);
            turnLeft(num(20)); //180˚
            forward(num(10), 0.5);
            turnRight(num(10));

            for (int i = 0; i < 4; i++){
                arm_left.setPosition(0.9);
                arm_right.setPosition(0.5);
                arm_left.setPosition(0.5);
                arm_right.setPosition(0.2);
                arm_left.setPosition(0.9);
                arm_right.setPosition(0.5);
                arm_left.setPosition(0.5);
                arm_right.setPosition(0.2);
                arm_left.setPosition(0.9);
                arm_right.setPosition(0.5);
                arm_left.setPosition(0.5);
                arm_right.setPosition(0.2);

                arm_left.setPosition(0.7);
                arm_right.setPosition(0.33);
                backward(num(10), 1);


                shuffleRight(num(7));
                shuffleLeft(num(7));
                arm_left.setPosition(0.9);
                arm_right.setPosition(0.7);
                turnRight(num(40));

                arm_left.setPosition(0.7);
                arm_right.setPosition(0.33);
                forward(num(10), 1);

                arm_left.setPosition(0.9);
                arm_right.setPosition(0.7);
                turnLeft(num(40));

                arm_left.setPosition(0.7);
                arm_right.setPosition(0.33);
                shuffleRight(num(5));
                shuffleLeft(num(5));

                arm_left.setPosition(0.9);
                arm_right.setPosition(0.7);
                sleep(1000);
                arm_left.setPosition(0.5);
                arm_right.setPosition(0.2);

                turnRight(num(10));
                arm_left.setPosition(0.7);
                arm_right.setPosition(0.33);
                forward(num(7), 1);

                turnLeft(num(20));
                forward(num(7), 1);

                turnRight(num(10));
                arm_left.setPosition(0.5);
                arm_right.setPosition(0.2);
                forward(num(5), 1);

                arm_left.setPosition(0.7);
                arm_right.setPosition(0.33);
                backward(num(5), 1);
            }
        }
    }

    private void setPower(double power){
        right_front.setPower(0.7*power);
        right_back.setPower(0.7*power);
        left_front.setPower(0.7*power);
        left_back.setPower(0.7*power);
    }

    public int num (float inches_to_move) {
        float num_inches_per_rev = 3*(float)Math.PI;
        float counts_to_move = (inches_to_move/num_inches_per_rev)*1120;
        int rounded_counts_to_move = Math.round(counts_to_move);
        return (rounded_counts_to_move);
    }

    public void run (){
        right_front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void forward (int x, double power){
        right_front.setTargetPosition(right_front.getCurrentPosition()+x);
        right_back.setTargetPosition(right_back.getCurrentPosition()+x);
        left_front.setTargetPosition(left_front.getCurrentPosition()+x);
        left_back.setTargetPosition(left_back.getCurrentPosition()+x);

        run();

        setPower(power);

        while (opModeIsActive() && left_front.isBusy() && left_back.isBusy() && right_front.isBusy() && right_back.isBusy()) {
        }
        setPower(0);
    }

    private void backward (int x, double power){
        right_front.setTargetPosition(right_front.getCurrentPosition()-x);
        right_back.setTargetPosition(right_back.getCurrentPosition()-x);
        left_front.setTargetPosition(left_front.getCurrentPosition()-x);
        left_back.setTargetPosition(left_back.getCurrentPosition()-x);

        run();

        setPower(power);

        while (opModeIsActive() && left_front.isBusy() && left_back.isBusy() && right_front.isBusy() && right_back.isBusy()) {
        }
        setPower(0);

    }

    private void shuffleLeft (int x){
        right_front.setTargetPosition(right_front.getCurrentPosition()+x);
        right_back.setTargetPosition(right_back.getCurrentPosition()-x);
        left_front.setTargetPosition(left_front.getCurrentPosition()-x);
        left_back.setTargetPosition(left_back.getCurrentPosition()+x);

        run();

        setPower(1);

        while (opModeIsActive() && left_front.isBusy() && left_back.isBusy() && right_front.isBusy() && right_back.isBusy()) {
        }
        setPower(0);
    }

    private void shuffleRight (int x){
        right_front.setTargetPosition(right_front.getCurrentPosition()-x);
        right_back.setTargetPosition(right_back.getCurrentPosition()+x);
        left_front.setTargetPosition(left_front.getCurrentPosition()+x);
        left_back.setTargetPosition(left_back.getCurrentPosition()-x);

        run();

        setPower(1);

        while (opModeIsActive() && left_front.isBusy() && left_back.isBusy() && right_front.isBusy() && right_back.isBusy()) {
        }
        setPower(0);
    }

    private void turnRight (int x){
        right_front.setTargetPosition(right_front.getCurrentPosition()-x);
        right_back.setTargetPosition(right_back.getCurrentPosition()-x);
        left_front.setTargetPosition(left_front.getCurrentPosition()+x);
        left_back.setTargetPosition(left_back.getCurrentPosition()+x);

        run();

        setPower(1);

        while (opModeIsActive() && left_front.isBusy() && left_back.isBusy() && right_front.isBusy() && right_back.isBusy()) {
        }
        setPower(0);
    }

    private void turnLeft (int x){
        right_front.setTargetPosition(right_front.getCurrentPosition()+x);
        right_back.setTargetPosition(right_back.getCurrentPosition()+x);
        left_front.setTargetPosition(left_front.getCurrentPosition()-x);
        left_back.setTargetPosition(left_back.getCurrentPosition()-x);

        run();

        setPower(1);

        while (opModeIsActive() && left_front.isBusy() && left_back.isBusy() && right_front.isBusy() && right_back.isBusy()) {
        }
        setPower(0);
    }
}


