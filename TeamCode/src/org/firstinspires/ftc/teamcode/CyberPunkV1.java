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
            double rotate = -gamepad1.right_stick_x * 0.5;

            if (Math.abs(powerX) > Math.abs(powerY)) {
                robot.stangaSpate.setPower(powerX + rotate);
                robot.dreaptaSpate.setPower(powerX + rotate);
                robot.stangaFata.setPower(-powerX + rotate);
                robot.dreaptaFata.setPower(-powerX + rotate);
            }
            else {
                robot.stangaFata.setPower(powerY + rotate);
                robot.stangaSpate.setPower(powerY + rotate);
                robot.dreaptaFata.setPower(-powerY + rotate);
                robot.dreaptaSpate.setPower(-powerY + rotate);
            }

            if (gamepad1.a) {
                robot.gheara.setPosition(1);
            }
            if (gamepad1.y) {
                robot.gheara.setPosition(0);
            }

            if (gamepad1.x) {
                robot.ridicareBrat.setPower(1);
            }
            else if (gamepad1.b) {
                robot.ridicareBrat.setPower(-1);
            }
            else {
                robot.ridicareBrat.setPower(0);
            }
        }
    }
}
