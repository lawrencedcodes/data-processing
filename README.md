WeatherDashboard: A Pure Java SPA Proof-of-Concept
A lightweight, server-side rendered Single Page Application (SPA) demonstrating how to build modern, type-safe web interfaces without leaving the JVM.

Built with Spring Boot and Vaadin Flow.

🚀 The "Why"
In a landscape dominated by complex JavaScript frontend frameworks, context switching between a Java backend and a React/Angular frontend creates cognitive load and introduces integration fragility.

This project demonstrates a "Backend-for-Frontend" (BFF) pattern where the UI logic resides entirely on the server. This approach allows backend engineers to:

Maintain Type Safety: End-to-end typing from the database to the DOM.

Accelerate Velocity: Skip the REST API glue code and render views directly from service data.

Reduce Complexity: No node_modules, no Webpack, just Maven.

🛠 Tech Stack
Framework: Spring Boot 3.x

UI Layer: Vaadin Flow (Java-based component architecture)

HTTP Client: java.net.http.HttpClient (Native Java 11+ client)

JSON Processing: Jackson

Data Source: OpenWeatherMap API

📋 Prerequisites
Java JDK 17+

Maven 3.8+

An active OpenWeatherMap API Key

⚙️ Configuration & Setup
Security Note: This application requires an API Key. Do not hardcode credentials. The application is configured to look for the key in your environment variables.

1. Clone the repository
Bash
git clone https://github.com/lawrencedcodes/data-processing.git
cd data-processing
2. Set your API Key
Export your OpenWeatherMap key as an environment variable:

Mac/Linux:

Bash
export OPENWEATHER_API_KEY="your_actual_api_key_here"
Windows (PowerShell):

PowerShell
$env:OPENWEATHER_API_KEY="your_actual_api_key_here"
3. Run the Application
Bash
mvn spring-boot:run
Once the application starts, navigate to http://localhost:8080.

🏗 Architecture Overview
The application follows a streamlined Model-View-Service architecture:

DataService.java:

Acts as the data layer.

Constructs a synchronous HTTP request to OpenWeatherMap.

Parses the raw JSON payload into a typed result (Celsius conversion handles here).

Design Choice: Uses native java.net.http to avoid heavy external dependencies for simple REST calls.

DataView.java:

Acts as the presentation layer.

Defined via the @Route("") annotation.

Injects DataService to fetch live data.

Constructs the HTML DOM programmatically using Java objects (H1, Paragraph).

🔮 Future Improvements (Roadmap)
To move this from a functional POC to a production-grade dashboard, the following updates are planned:

Asynchronous Loading: Move the data fetching from the Constructor to the onAttach lifecycle method using ui.access() to prevent blocking the UI rendering thread during API latency.

Caching: Implement Spring Cache to reduce API calls for the same city within a 10-minute window.

Error Handling: Add a UI notification system (Vaadin Notification.show()) to gracefully handle 401/404 errors from the external API.

🤝 Contributing
Issues and Pull Requests are open. Please ensure all new UI components are built using the Vaadin component library to maintain consistency.
