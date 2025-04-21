def chatbot():
    print("🛍️ Welcome to ShopEase Bot!")
    print("How can I help you today?")
    print("Type 'exit' to end the chat.\n")

    while True:
        user_input = input("You: ").lower()

        if "exit" in user_input:
            print("Bot: Thank you for visiting! Have a great day! 👋")
            break

        elif "hello" in user_input or "hi" in user_input:
            print("Bot: Hello! How can I assist you today? 😊")

        elif "store hours" in user_input or "open" in user_input:
            print("Bot: We're open from 9 AM to 9 PM, Monday to Saturday. 🕘")

        elif "shipping" in user_input:
            print("Bot: We offer free shipping on orders over $50. Delivery takes 3-5 business days. 🚚")

        elif "return" in user_input or "refund" in user_input:
            print("Bot: You can return items within 30 days of purchase with a valid receipt. 🔄")

        elif "contact" in user_input or "support" in user_input:
            print("Bot: You can contact support at support@shopease.com or call 1-800-123-4567. ☎️")

        elif "track order" in user_input:
            print("Bot: To track your order, please visit our website and go to 'My Orders'. 🧾")

        else:
            print("Bot: I'm sorry, I didn't understand that. Could you please rephrase? 🤔")

if __name__ == "__main__":
    chatbot()
