from fastapi import FastAPI
from pydantic import BaseModel
from typing import List
from ai_engine import AIWorkflow

app = FastAPI()
ai_pipeline = AIWorkflow()

class ChatRequest(BaseModel):
    user_id: str
    message: str

class ChatResponse(BaseModel):
    reply: str
    context_used: List[str]

@app.post("/chat", response_model=ChatResponse)
async def chat_endpoint(request: ChatRequest):
    # Execute optimized AI workflow pipeline
    reply, context = ai_pipeline.process(request.user_id, request.message)
    return ChatResponse(reply=reply, context_used=context)

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)