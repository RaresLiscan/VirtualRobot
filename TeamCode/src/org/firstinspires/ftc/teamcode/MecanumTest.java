package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="MecanumTest")
public class MecanumTest extends LinearOpMode {

    private DcMotor stangaFata = null;
    private DcMotor stangaSpate = null;
    private DcMotor dreaptaFata = null;
    private DcMotor dreaptaSpate = null;
    private Servo servo = null;
    private Servo gheara = null;
    private DistanceSensor front = null;
    private DistanceSensor back = null;
    private DistanceSensor right = null;
    private DistanceSensor left = null;

    @Override
    public void runOpMode() throws InterruptedException {

        stangaSpate = hardwareMap.dcMotor.get("back_left_motor");
        stangaFata = hardwareMap.dcMotor.get("front_left_motor");
        dreaptaFata = hardwareMap.dcMotor.get("front_right_motor");
        dreaptaSpate = hardwareMap.dcMotor.get("back_right_motor");
        servo = hardwareMap.servo.get("back_servo");
        servo.setPosition(0.5);
        gheara = hardwareMap.servo.get("gheara");
        gheara.setPosition(0);

        front = hardwareMap.get(DistanceSensor.class, "front_distance");
        back = hardwareMap.get(DistanceSensor.class, "back_distance");
        left = hardwareMap.get(DistanceSensor.class, "left_distance");
        right = hardwareMap.get(DistanceSensor.class, "right_distance");

        waitForStart();

        while (opModeIsActive()) {
            double rotate = -gamepad1.right_stick_x;
            double powerX = -gamepad1.left_stick_x;
            double powerY = gamepad1.right_trigger != 0 ? -gamepad1.right_trigger : gamepad1.left_trigger;

            if (Math.abs(powerY) >= Math.abs(powerX)) {
                stangaSpate.setPower(powerY + rotate);
                dreaptaFata.setPower(-powerY + rotate);
                stangaFata.setPower(powerY + rotate);
                dreaptaSpate.setPower(-powerY + rotate);
            }
            else {
                stangaFata.setPower(powerX + rotate);
                stangaSpate.setPower(-powerX + rotate);
                dreaptaSpate.setPower(-powerX + rotate);
                dreaptaFata.setPower(powerX + rotate);
            }

            servo.setPosition(gamepad1.left_stick_y * 0.5 + 0.5);
            if (gamepad1.a) {
                gheara.setPosition(1);
            }

            if (gamepad1.y) {
                gheara.setPosition(0);
            }

            telemetry.addData("Front: ", front.getDistance(DistanceUnit.CM));
            telemetry.addData("Back: ", back.getDistance(DistanceUnit.CM));
            telemetry.addData("Left: ", left.getDistance(DistanceUnit.CM));
            telemetry.addData("Right: ", right.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
    }
}
