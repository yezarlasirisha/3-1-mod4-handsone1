class AIWorkflow:
    def __init__(self):
        # In a real scenario, connect to ChromaDB or Pinecone here
        self.vector_store = {}

    def retrieve_context(self, user_id: str, query: str):
        # Mock retrieval from vector DB
        history = self.vector_store.get(user_id, [])
        return history[-3:] # Return last 3 memories

    def generate_response(self, query: str, context: list):
        # Mock LLM call (OpenAI/Anthropic/Local)
        if not context:
            return "I have no memory of our past conversations."
        return f"Based on our past conversations about {context}, I can answer your question about {query}."

    def process(self, user_id: str, message: str):
        # 1. Retrieve Memory
        context = self.retrieve_context(user_id, message)

        # 2. Generate Response
        response = self.generate_response(message, context)

        # 3. Save new memory
        if user_id not in self.vector_store:
            self.vector_store[user_id] = []
        self.vector_store[user_id].append(message)

        return response, context