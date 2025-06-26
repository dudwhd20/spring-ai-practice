from optimum.exporters.onnx import main_export
from transformers import AutoTokenizer
from pathlib import Path

model_id = "intfloat/multilingual-e5-large-instruct"
output_dir = "./onnx-e5-model"

Path(output_dir).mkdir(parents=True, exist_ok=True)

main_export(
    model_name_or_path=model_id,
    output=output_dir,
    task="feature-extraction",
    tokenizer=AutoTokenizer.from_pretrained(model_id),
)
