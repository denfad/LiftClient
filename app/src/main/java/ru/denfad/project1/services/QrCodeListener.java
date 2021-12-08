package ru.denfad.project1.services;

public interface QrCodeListener {
    void onQRCodeFound(String qrCode);
    void qrCodeNotFound();
}
