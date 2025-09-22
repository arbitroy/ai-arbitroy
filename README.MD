# AI Arbitroy - Spring Boot AI Chat & Feedback System

A Spring Boot application that integrates Google Gemini AI for chat functionality and intelligent customer feedback analysis.

## Features

### 🤖 AI Chat
- Real-time chat with Google Gemini AI
- Clean, responsive web interface
- HTMX-powered seamless interactions

### 📊 Smart Feedback Analysis
- **Automatic categorization**: Compliment, Complaint, Suggestion, Query
- **Sentiment analysis**: Positive, Negative, Neutral
- **AI-generated titles**: Short, descriptive summaries
- **Visual dashboard**: Color-coded categories and sentiment indicators

## Tech Stack

- **Backend**: Spring Boot 3.5.6, Java 17
- **Frontend**: Thymeleaf, HTMX, Tailwind CSS
- **AI**: Google Vertex AI Gemini
- **Database**: H2 (in-memory)
- **Build Tool**: Maven

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- Google Cloud Project with Vertex AI enabled

### Setup

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd ai-arbitroy
   ```

2. **Configure environment variables**
   ```bash
   cp .env.example .env
   ```
   
   Update `.env` with your Google Cloud credentials:
   ```
   GEMINI_PROJECT_ID=your_google_cloud_project_id
   GEMINI_LOCATION=us-central1
   GEMINI_MODEL=gemini-pro
   GEMINI_TEMP=0.7
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the application**
   - Chat: http://localhost:8082
   - Feedback: http://localhost:8082/feedback

## Usage

### Chat Interface
- Navigate to the home page
- Type messages and get AI responses instantly
- Clean, mobile-friendly interface

### Feedback System
- Fill out the feedback form (name, product, comment)
- AI automatically analyzes and categorizes feedback
- View results in the live dashboard table
- Categories: Compliment 😊, Complaint 😞, Suggestion 💡, Query ❓

## API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/` | GET | Chat interface |
| `/feedback` | GET | Feedback form page |
| `/chat` | POST | Submit chat message (HTMX) |
| `/submit-feedback` | POST | Submit feedback for AI analysis (HTMX) |
| `/api/google-gemini/chat` | POST | REST API for chat |

## Project Structure

```
src/main/java/com/concept/ai_arbitroy/
├── Controller/
│   ├── WebChatController.java          # Chat interface
│   ├── CustomerFeedBackController.java # Feedback system
│   └── GoogleGeminiChatController.java # REST API
├── Service/
│   ├── SimpleChatService.java          # Basic chat service
│   ├── FeedbackService.java            # Feedback management
│   └── FeedbackAnalysisService.java    # AI analysis logic
├── entity/
│   └── Feedback.java                   # Feedback data model
└── dto/
    ├── ChatResponse.java               # Chat API response
    └── FeedbackAnalysis.java           # AI analysis result
```

## Configuration

The application uses the following configuration files:
- `application.properties` - Spring Boot configuration
- `.env` - Environment variables (Google Cloud credentials)

## Development

### Adding New Features
1. Create new controllers in the `Controller` package
2. Add business logic to the `Service` package
3. Create Thymeleaf templates in `src/main/resources/templates/`
4. Use HTMX for dynamic interactions

### Database
Currently uses H2 in-memory database. To use a persistent database, update dependencies in `pom.xml` and configuration in `application.properties`.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is open source and available under the [MIT License](LICENSE).

## Support

For issues and questions, please create an issue in the repository or contact the development team.