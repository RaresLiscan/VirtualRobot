package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotMap {

    public DcMotor stangaFata = null;
    public DcMotor stangaSpate = null;
    public DcMotor dreaptaFata = null;
    public DcMotor dreaptaSpate = null;
    public DcMotor ridicareBrat = null;
    public DcMotor scripeteSlide = null;
    public Servo gheara = null;

    public RobotMap (HardwareMap hardwareMap) {
        stangaFata = hardwareMap.get(DcMotor.class, "stangaFata");
        dreaptaFata = hardwareMap.get(DcMotor.class, "dreaptaFata");
        stangaSpate = hardwareMap.get(DcMotor.class, "stangaSpate");
        dreaptaSpate = hardwareMap.get(DcMotor.class, "dreaptaSpate");
        ridicareBrat = hardwareMap.get(DcMotor.class, "ridicareBrat");
        scripeteSlide = hardwareMap.get(DcMotor.class, "scripeteSlide");
        gheara = hardwareMap.get(Servo.class, "gheara");
    }

}
