# Utilisez une image de base qui inclut déjà Java et installez également findutils
FROM openjdk:21

RUN apt-get update && apt-get install -y findutils

# Définition du répertoire de travail dans l'image
WORKDIR /app

# Copie des fichiers de votre application
COPY . .

# Exécution de la construction de votre application avec Gradle
RUN ./gradlew build -Pwar -x test

# Exposer le port de votre application si nécessaire
EXPOSE 8080

# Commande par défaut pour démarrer votre application (ajustez-la en fonction de votre application)
CMD ["java", "-jar", "build/libs/your-application.jar"]
