package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.RobotMap;

@Autonomous(name="TestAutonomous")
public class CyberPunkAutonomous extends LinearOpMode {

    RobotMap robot = null;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new RobotMap(hardwareMap);
        waitForStart();

        if (opModeIsActive()) {
            robot.stangaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.stangaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.dreaptaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.dreaptaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.stangaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.stangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.dreaptaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.dreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.stangaSpate.setTargetPosition(-5000);
            robot.stangaFata.setTargetPosition(-5000);
            robot.dreaptaSpate.setTargetPosition(5000);
            robot.dreaptaFata.setTargetPosition(5000);

            robot.stangaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.stangaFata.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.dreaptaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.dreaptaFata.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.stangaFata.setPower(1);
            robot.dreaptaFata.setPower(1);
            robot.dreaptaSpate.setPower(1);
            robot.stangaSpate.setPower(1);


            while (robot.stangaSpate.isBusy() && robot.stangaFata.isBusy() && robot.dreaptaSpate.isBusy() && robot.dreaptaFata.isBusy());

            robot.stopDriving();
        }
    }
}
