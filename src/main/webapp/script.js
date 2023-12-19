$(document).ready(function() {
    const chatBody = $("#chat-body");
    const userInput = $("#user-input");
    const sendButton = $("#send-button");

    sendButton.click(sendMessage);
    userInput.keydown(function(event) {
        if (event.key === "Enter") {
            sendMessage();
        }
    });

    function sendMessage() {
        const userMessage = userInput.val().trim();
        if (userMessage !== "") {
            addMessage(userMessage, "user-message");
            userInput.val("");
            getBotResponse(userMessage);
        }
    }

    function getBotResponse(userMessage) {
        $.ajax({
            url: "chatbot", // Hier den Namen des Servlets angeben
            method: "POST",
            data: { message: userMessage },
            success: function(response) {
                addMessage(response, "bot-message");
            },
            error: function() {
                addMessage("Sorry, something went wrong.", "bot-message");
            }
        });
    }

    function addMessage(message, className) {
        const messageElement = $("<div>").addClass("chat-message " + className).html("<p>" + message + "</p>");
        chatBody.append(messageElement);
        chatBody.scrollTop(chatBody.prop("scrollHeight"));
    }
});
