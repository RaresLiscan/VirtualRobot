package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import javafx.scene.shape.Rectangle;
import virtual_robot.controller.CyberPunk;

@TeleOp(name="CyberPunkTeleOP")
public class CyberPunkV1 extends LinearOpMode {

    private RobotMap robot = null;
    private double height = 45;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new RobotMap(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {





            if (robot.scripeteSlide.getPower() > 0 && height < 100) {
                height += robot.scripeteSlide.getPower();
            }
            if (robot.scripeteSlide.getPower() < 0 && height > 45) {
                height += robot.scripeteSlide.getPower();
            }

            telemetry.addData("Inaltimea bratului: ", height);
            telemetry.update();
        }
    }
}
