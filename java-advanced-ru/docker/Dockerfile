# Указываем образ, от которого наследуемся
FROM gradle:7.2.0-jdk16

# ARG JAR_FILE=build/libs/*.jar
ARG JAR_FILE=out/artifacts/docker_jar/*.jar
COPY ${JAR_FILE} my-app-image.jar

# Устанавливаем рабочую директорию
# WORKDIR /project
# Копируем директорию из основной файловой системы внутрь образа
# COPY ./project .

# Определяет действие, которое будет выполнено при старте контейнера (docker run)
# Используется только в случае, если контейнер был запущен без указания команды
# [команда, аргумент]
CMD ["echo", "Hello Hexlet!"]

# Сообщаем, что контейнер использует 8000 порт
EXPOSE 3000

ENTRYPOINT ["java", "-jar", "my-app-image.jar"]
