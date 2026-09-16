package zhulikov.project.notificationservice.enums;

public enum StatusType {
    PENDING,    // Ожидает отправки
    SENT,       // Отправлено успешно
    FAILED,     // Ошибка при отправке
    RETRY,      // Будет повторная попытка
    CANCELLED   // Отменено
}
