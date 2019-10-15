package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="AutonomousTest")
public class AutonomousTest extends LinearOpMode {

    private DcMotor stangaFata = null;
    private DcMotor stangaSpate = null;
    private DcMotor dreaptaFata = null;
    private DcMotor dreaptaSpate = null;
    private Servo servo = null;

    private void moveWithEncoder (int ticks, double power, double timeout) {
        stangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        stangaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dreaptaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        stangaFata.setTargetPosition(-ticks);
        stangaSpate.setTargetPosition(-ticks);
        dreaptaFata.setTargetPosition(ticks);
        dreaptaSpate.setTargetPosition(ticks);

        stangaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        stangaFata.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dreaptaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dreaptaFata.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        stangaFata.setPower(power);
        stangaSpate.setPower(power);
        dreaptaFata.setPower(power);
        dreaptaSpate.setPower(power);

        while (stangaSpate.isBusy() && stangaFata.isBusy() && dreaptaSpate.isBusy() && dreaptaFata.isBusy());

        stopMoving();
    }

    private void stopMoving () {
        stangaFata.setPower(0);
        stangaSpate.setPower(0);
        dreaptaFata.setPower(0);
        dreaptaSpate.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        stangaSpate = hardwareMap.dcMotor.get("back_left_motor");
        stangaFata = hardwareMap.dcMotor.get("front_left_motor");
        dreaptaFata = hardwareMap.dcMotor.get("front_right_motor");
        dreaptaSpate = hardwareMap.dcMotor.get("back_right_motor");
        servo = hardwareMap.servo.get("back_servo");
        servo.setPosition(0);

        stangaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dreaptaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        stangaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dreaptaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        if (opModeIsActive()) {
            moveWithEncoder(3000, 1, 10);
        }
    }
}
