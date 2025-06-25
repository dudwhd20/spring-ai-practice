from optimum.onnxruntime import ORTModelForFeatureExtraction
from transformers import AutoTokenizer
import os

# 기존 huggingface 모델 경로
model_path = "/Users/youngjongso/dev/triger-agent-demo/models/all-MiniLM-L6-v2/snapshots/c9745ed1d9f207416be6d2e6f8de32d1f16199bf"

# ONNX 저장할 디렉토리
output_path = "/Users/youngjongso/dev/triger-agent-demo/models/onnx-all-MiniLM-L6-v2"

# 디렉토리 생성
os.makedirs(output_path, exist_ok=True)

# 토크나이저 저장 (이건 거의 변환 필요 없음)
tokenizer = AutoTokenizer.from_pretrained(model_path)
tokenizer.save_pretrained(output_path)

# ONNX 변환
model = ORTModelForFeatureExtraction.from_pretrained(
    model_path,
    export=True,
    provider="CPUExecutionProvider",
)

# 모델 저장
model.save_pretrained(output_path)

print("✅ ONNX 변환 완료:", output_path)
