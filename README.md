# Spring AI 및 RAG + DB Tool Chain 실습

이 프로젝트는 **Spring AI**를 활용한 RAG (Retrieval-Augmented Generation) 시스템을 구성하는 실습입니다.

* Vector DB: **Chroma DB**
* DB Tool: **PostgreSQL**
* LLM: **Ollama (Local LLM Engine)**

---

## 폴더 구조

```
project-root/
|
|├️ chroma-compose/      # Chroma DB Docker Compose & Postgres
|   └️ docker-compose.yml
|
|├️ src/                 # Spring Boot (Spring AI) 소스코드
|
└️ README.md            # 보기 문서
```

프로젝트 구조는 헥사고날 형식으로 구성되어 있습니다.

* RAG 보드: Retrieval 관련 기능 구현
* LLM 보드: Ollama LLM 구현 및 해상 방법 구현
* DB Tool 보드: PostgresSQL 데이터 및 Tool 연계 방법 구현 (아직 안만듬)

---

## 사전 준비 필요 항목

### 1. Docker 설치 (Chroma DB 실행용)

* [https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop)

### 2. Ollama 설치 (Local LLM Engine)

* [https://ollama.com/download](https://ollama.com/download)

#### 설치 후 확인

```bash
ollama --version
ollama list
```

#### 다음의 LLM 모델 다운로드

```bash
ollama pull llama3.1
ollama run llama3.1
```

---

## Chroma DB 실행 방법

### 1. `chroma-compose` 폴더 이동

```bash
cd chroma-compose
```

### 2. Docker Compose 실행

```bash
docker-compose up -d
```

* SQLite Embedded 모드로 Chroma DB 구보
* REST API: `localhost:8123`
* DB 데이터는 `(chroma-compose/chroma_data)` 폴더에 저장
* PostgreSQL 임시 직원 휴가 데이터 자동 생성 스크립트 작성 되어 있음
* PostgreSQL 데이터베이스 name triger / schema public / table leave
---

---

## 실습 목표

* Spring-AI Retriever 대로 Chroma DB 검색
* Ollama 를 이용한 LLM 추리
* PostgreSQL 에 보안된 기본 데이터 관리
* ToolChain 로 DB 데이터도 LLM에 전달


1. 테스트 데이터 -> Embedding -> Chroma 저장
2. 사용자의 질문 -> Retriever 검색 -> 관련 문서 발견
3. Ollama LLM에 문서 + DB Tool 결과와 같이 출력

---

## 확장 계획
* Retrieval + ToolUsage 합합 Chain 구성 가능
