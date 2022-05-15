package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Iterative;
@TeleOp(name="Dancing Robot Manual2")

public class manualDR2 extends BasicOpMode_Iterative {
    private DcMotor rightFront = null;
    private DcMotor rightRear = null;
    private DcMotor leftFront = null;
    private DcMotor leftRear = null;

    private Servo arm_left = null;
    private Servo arm_right = null;


    @Override
    public void init() {
        rightFront = hardwareMap.dcMotor.get("rf");
        rightRear = hardwareMap.dcMotor.get("rb");
        leftFront = hardwareMap.dcMotor.get("lf");
        leftRear = hardwareMap.dcMotor.get("lb");

        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);

        arm_left = hardwareMap.servo.get("al");
        arm_right = hardwareMap.servo.get("ar");
        arm_left.setPosition(0.7); //left arm parallel to ground facing forward
        arm_right.setPosition(0.33); //right arm parallel to ground facing forward
    }

    @Override
    public void loop() {

        final int COUNTS_PER_MOTOR_REV = 1120;
        //forward
        if (gamepad1.right_stick_y != 0) {
            //encoderoff();
            double power = gamepad1.right_stick_y;
            leftFront.setPower(-0.5*power);
            leftRear.setPower(-0.5*power);
            rightFront.setPower(-0.5*power);
            rightRear.setPower(-0.5*power);

            telemetry.addData("right stick y ", "value" + gamepad1.right_stick_y);
        }

        //turn
        if (gamepad1.left_stick_x != 0) {
            //encoderoff();
            double turn = gamepad1.left_stick_x;
            leftFront.setPower(-0.5 * turn) ;
            leftRear.setPower(-0.5 * turn);
            rightFront.setPower(0.5 * turn);
            rightRear.setPower(0.5 * turn);
            telemetry.addData("left stick x ", "value" + gamepad1.left_stick_x);

        }
        if ((gamepad1.left_trigger == 0) && (gamepad1.right_trigger == 0) && (gamepad1.left_stick_x == 0) && (gamepad1.right_stick_y == 0)) {
            setPower(0);
            telemetry.addData("left trigger", "value" + gamepad1.left_trigger);
        }
        boolean shuffleright = gamepad1.right_bumper;
        if (shuffleright) {
            double power = 0.75;
            leftFront.setPower(-0.75 * power);
            leftRear.setPower(0.75 * power);
            rightFront.setPower(0.75 * power);
            rightRear.setPower(-0.75 * power);

            telemetry.addData("right stick y ", "value" + gamepad1.right_stick_y);
        }

        boolean shuffleleft = gamepad1.left_bumper;
        if (shuffleleft) {
            double power = 0.75;
            leftFront.setPower(0.75 * power);
            leftRear.setPower(-0.75 * power);
            rightFront.setPower(-0.75 * power);
            rightRear.setPower(0.75 * power);

            telemetry.addData("right stick y ", "value" + gamepad1.right_stick_y);
        }






        //gamepad 2


        boolean hArmR2 = gamepad2.a;
        if (hArmR2) {
            arm_right.setPosition(0.5);
        }

        boolean hArmL2 = gamepad2.b;
        if (hArmL2) {
            arm_left.setPosition(0.9);
        }

        boolean lArmR2 = gamepad2.x;
        if (lArmR2) {
            arm_right.setPosition(0.2);
        }

        boolean lArmL2 = gamepad2.y;
        if (lArmL2) {
            arm_left.setPosition(0.4);
        }
    }


    private void setPower(double power) {
        leftFront.setPower(0.35*power);
        leftRear.setPower(0.35*power);
        rightFront.setPower(0.35*power);
        rightRear.setPower(0.35*power);
    }

    private void encoderoff() {
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void encoderon() {
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


}

