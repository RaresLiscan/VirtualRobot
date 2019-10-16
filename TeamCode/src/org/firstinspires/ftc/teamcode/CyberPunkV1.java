package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="CyberPunkTeleOP")
public class CyberPunkV1 extends LinearOpMode {

    RobotMap robot = null;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new RobotMap(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            double powerY = gamepad1.left_stick_y * 0.5;
            double powerX = gamepad1.left_stick_x * 0.5;

            if (Math.abs(powerX) > Math.abs(powerY)) {
                robot.stangaSpate.setPower(powerX);
                robot.dreaptaSpate.setPower(powerX);
                robot.stangaFata.setPower(-powerX);
                robot.dreaptaFata.setPower(-powerX);
            }
            else {
                robot.stangaFata.setPower(powerY);
                robot.stangaSpate.setPower(powerY);
                robot.dreaptaFata.setPower(-powerY);
                robot.dreaptaSpate.setPower(-powerY);
            }
        }
    }
}
