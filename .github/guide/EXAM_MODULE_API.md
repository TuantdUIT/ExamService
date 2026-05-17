# Exam Module API

## 1. Mục đích module

Module `Exam` dùng để quản lý đề thi / bài tập trong `ExamService`, bao gồm:

- tạo đề mới
- xem chi tiết đề
- tìm kiếm danh sách đề
- cập nhật cấu trúc đề
- cập nhật trạng thái đề

Module này hỗ trợ đồng thời 2 cách đưa câu hỏi vào đề:

- câu hỏi lẻ qua `examQuestions`
- nhóm câu hỏi qua `examQuestionGroups`

Với `examQuestionGroups`, backend hiện hỗ trợ 2 mode:

- dùng `question group` có sẵn từ module riêng
- tạo mới `question group` ngay trong lúc tạo hoặc cập nhật đề

---

## 2. Quy ước response chung

Tất cả API thành công của module này đều được bọc bởi `RestResponse`.

### Format thành công chung

```json
{
  "statusCode": 200,
  "message": "Call API success",
  "data": {}
}
```

Ví dụ:

```json
{
  "statusCode": 200,
  "message": "Create exam",
  "data": {}
}
```

### Format lỗi chung

```json
{
  "statusCode": 400,
  "message": "Bad Request",
  "error": "Nội dung lỗi",
  "data": null
}
```

Hoặc với lỗi validation:

```json
{
  "statusCode": 400,
  "message": "Bad Request",
  "error": "Validation failed",
  "data": {
    "fieldName": "Chi tiết lỗi"
  }
}
```

---

## 3. DTO chính của module

### 3.1. Cấu trúc response chi tiết đề

```json
{
  "examUuid": "uuid",
  "examName": "string",
  "gradeId": 12,
  "examType": "QUIZ | HOMEWORK | MOCK_TEST | OFFICIAL_TEST",
  "startTime": "2026-05-17T10:00:00Z | null",
  "endTime": "2026-05-17T11:00:00Z | null",
  "durationMinutes": 45,
  "totalScore": 10.0,
  "numberOfAttempt": 1,
  "status": "DRAFT | PUBLISHED | CLOSED | ARCHIVED",
  "createdByUserUuid": "uuid",
  "tfCorrect1Pct": 10,
  "tfCorrect2Pct": 25,
  "tfCorrect3Pct": 50,
  "tfCorrect4Pct": 100,
  "questionSummary": {
    "mcqCount": 10,
    "tfqCount": 4,
    "saqCount": 2
  },
  "questionSections": [],
  "createdAt": "2026-05-17T10:00:00Z",
  "updatedAt": "2026-05-17T10:00:00Z",
  "createdBy": "string | null",
  "updatedBy": "string | null"
}
```

### 3.2. Cấu trúc `questionSections`

`questionSections` luôn được tổ chức theo từng loại câu hỏi:

- `MCQ`
- `TFQ`
- `SAQ`

Mỗi section có format:

```json
{
  "questionType": "MCQ | TFQ | SAQ",
  "totalQuestionCount": 5,
  "standaloneQuestions": [],
  "groups": []
}
```

### 3.3. Cấu trúc câu hỏi lẻ trong đề

```json
{
  "examQuestionUuid": "uuid",
  "questionUuid": "uuid",
  "questionOrder": 1,
  "score": 1.0,
  "sectionType": "MCQ | TFQ | SAQ",
  "sourceType": "MANUAL | QUESTION_BANK | IMPORTED",
  "questionDetail": {
    "questionUuid": "uuid",
    "questionContent": "string",
    "questionTopic": "string | null",
    "questionType": "MCQ | TFQ | SAQ"
  }
}
```

### 3.4. Cấu trúc nhóm câu hỏi trong đề

```json
{
  "eqgUuid": "uuid",
  "questionGroupUuid": "uuid",
  "groupName": "string",
  "questionType": "MCQ | TFQ | SAQ",
  "questionTopic": "string | null",
  "poolQuestionCount": 10,
  "pickQuestionCount": 4,
  "scorePerQuestion": 0.5,
  "displayOrder": 1,
  "items": [
    {
      "eqgiUuid": "uuid",
      "questionUuid": "uuid",
      "questionDetail": {
        "questionUuid": "uuid",
        "questionContent": "string",
        "questionTopic": "string | null",
        "questionType": "MCQ | TFQ | SAQ"
      }
    }
  ]
}
```

---

## 4. Rule dữ liệu quan trọng

### 4.1. Rule chung của đề

- `gradeId` có kiểu `Long`
- `endTime` phải sau `startTime` nếu cả hai cùng có giá trị
- `durationMinutes` phải lớn hơn hoặc bằng `0`
- `totalScore` phải lớn hơn `0`
- `numberOfAttempt` phải lớn hơn hoặc bằng `0`
- `status` là bắt buộc

### 4.2. Rule phần trăm chấm `TFQ`

- `tfCorrect1Pct`
- `tfCorrect2Pct`
- `tfCorrect3Pct`
- `tfCorrect4Pct`

phải không giảm, tức là:

- `tfCorrect1Pct <= tfCorrect2Pct <= tfCorrect3Pct <= tfCorrect4Pct`

Nếu không truyền, backend sẽ gán mặc định:

- `10`
- `25`
- `50`
- `100`

### 4.3. Rule câu hỏi lẻ `examQuestions`

- `questionOrder` phải unique trong cùng một đề
- `questionUuid` phải tồn tại trong ngân hàng câu hỏi
- `sectionType` phải khớp với `questionType` của câu hỏi gốc

### 4.4. Rule nhóm câu hỏi `examQuestionGroups`

- `displayOrder` phải unique trong cùng một đề
- mỗi phần tử `examQuestionGroups` phải chọn đúng 1 trong 2 cách:
  - truyền `questionGroupUuid`
  - hoặc truyền `newQuestionGroup`
- `pickQuestionCount` phải nhỏ hơn hoặc bằng `questionCount` của group thực tế
- `displayOrder` là thuộc tính của đề, không phải thuộc tính của `question group` gốc
- `scorePerQuestion` là thuộc tính của đề, không phải thuộc tính của `question group` gốc

### 4.5. Ý nghĩa `questionCount` và `pickQuestionCount`

- `questionCount`: số câu hiện có trong pool của `question group`
- `pickQuestionCount`: số câu sẽ được random ra khi học sinh bắt đầu `attempt`

Ví dụ:

- group có `10` câu trong pool
- chỉ lấy `4` câu khi học sinh bắt đầu làm bài

thì:

- `questionCount = 10`
- `pickQuestionCount = 4`

---

## 5. API chi tiết

## 5.1. Tạo đề

### Đường dẫn

`POST /api/v1/exams`

### Mô tả luồng

Tạo đề mới -> validate dữ liệu chung của đề -> validate phần trăm chấm `TFQ` -> validate danh sách câu hỏi lẻ -> resolve từng phần tử `examQuestionGroups` theo mode `questionGroupUuid` hoặc `newQuestionGroup` -> nếu là `newQuestionGroup` thì tạo `question group` riêng trước -> kiểm tra toàn bộ `questionUuid` có tồn tại và đúng loại hay không -> lưu `Exam` xuống database -> snapshot group vào `ExamQuestionGroup` và `ExamQuestionGroupItem` -> build response theo cấu trúc `questionSummary` và `questionSections` -> trả kết quả

### Input format

```json
{
  "examName": "Đề kiểm tra Toán 15 phút",
  "gradeId": 12,
  "examType": "QUIZ",
  "startTime": "2026-05-18T01:00:00Z",
  "endTime": "2026-05-18T01:15:00Z",
  "durationMinutes": 15,
  "totalScore": 10,
  "numberOfAttempt": 1,
  "status": "DRAFT",
  "tfCorrect1Pct": 10,
  "tfCorrect2Pct": 25,
  "tfCorrect3Pct": 50,
  "tfCorrect4Pct": 100,
  "examQuestions": [
    {
      "questionUuid": "018f4a70-1111-7c11-8aa1-7c5d5b5b0101",
      "questionOrder": 1,
      "score": 1.0,
      "sectionType": "MCQ",
      "sourceType": "QUESTION_BANK"
    }
  ],
  "examQuestionGroups": [
    {
      "questionGroupUuid": "018f4a80-2222-7c11-8aa1-7c5d5b5b0301",
      "pickQuestionCount": 2,
      "scorePerQuestion": 0.5,
      "displayOrder": 1
    },
    {
      "newQuestionGroup": {
        "groupName": "Nguyên hàm",
        "questionType": "MCQ",
        "questionTopic": "Nguyên hàm",
        "questionCount": 5,
        "items": [
          {
            "questionUuid": "018f4a70-2222-7c11-8aa1-7c5d5b5b0201"
          },
          {
            "questionUuid": "018f4a70-2222-7c11-8aa1-7c5d5b5b0202"
          },
          {
            "questionUuid": "018f4a70-2222-7c11-8aa1-7c5d5b5b0203"
          },
          {
            "questionUuid": "018f4a70-2222-7c11-8aa1-7c5d5b5b0204"
          },
          {
            "questionUuid": "018f4a70-2222-7c11-8aa1-7c5d5b5b0205"
          }
        ]
      },
      "pickQuestionCount": 2,
      "scorePerQuestion": 0.5,
      "displayOrder": 2
    }
  ]
}
```

### Output format

```json
{
  "statusCode": 200,
  "message": "Create exam",
  "data": {
    "examUuid": "uuid",
    "examName": "Đề kiểm tra Toán 15 phút",
    "gradeId": 12,
    "examType": "QUIZ",
    "startTime": "2026-05-18T01:00:00Z",
    "endTime": "2026-05-18T01:15:00Z",
    "durationMinutes": 15,
    "totalScore": 10,
    "numberOfAttempt": 1,
    "status": "DRAFT",
    "createdByUserUuid": "uuid",
    "tfCorrect1Pct": 10,
    "tfCorrect2Pct": 25,
    "tfCorrect3Pct": 50,
    "tfCorrect4Pct": 100,
    "questionSummary": {
      "mcqCount": 3,
      "tfqCount": 0,
      "saqCount": 0
    },
    "questionSections": [
      {
        "questionType": "MCQ",
        "totalQuestionCount": 3,
        "standaloneQuestions": [],
        "groups": []
      },
      {
        "questionType": "TFQ",
        "totalQuestionCount": 0,
        "standaloneQuestions": [],
        "groups": []
      },
      {
        "questionType": "SAQ",
        "totalQuestionCount": 0,
        "standaloneQuestions": [],
        "groups": []
      }
    ],
    "createdAt": "2026-05-17T10:00:00Z",
    "updatedAt": "2026-05-17T10:00:00Z",
    "createdBy": "string | null",
    "updatedBy": "string | null"
  }
}
```

### Exception có thể trả về

#### `400 Bad Request`

- `Exam name must not be blank`
- `Grade id is required`
- `Exam type is required`
- `Duration minutes is required`
- `Total score is required`
- `Number of attempt is required`
- `Status is required`
- `End time must be after start time`
- `TF scoring percentages must be non-decreasing from 1 to 4 correct statements`
- `Question order must be unique within the exam`
- `Display order must be unique within exam question groups`
- `Each exam question group must provide either questionGroupUuid or newQuestionGroup`
- `Question not found with id: {questionUuid}`
- `Exam question section type must match question type for question id: {questionUuid}`
- `Question group not found with id: {questionGroupUuid}`
- `Pick question count must be less than or equal to pool size for group: {groupName}`
- `Group question type must match item question type for question id: {questionUuid}`
- `Group question topic must match item question topic for question id: {questionUuid}`

#### `403 Forbidden`

- khi JWT không đủ quyền truy cập

#### `500 Internal Server Error`

- lỗi không mong muốn từ backend

---

## 5.2. Lấy chi tiết đề

### Đường dẫn

`GET /api/v1/exams/{examUuid}`

### Mô tả luồng

Nhận `examUuid` -> tìm đề theo id -> lấy danh sách câu hỏi lẻ -> lấy danh sách group -> lấy toàn bộ `Question` liên quan -> build `questionSummary` -> build `questionSections` theo `MCQ/TFQ/SAQ` -> trả kết quả

### Input format

#### Path variable

- `examUuid`: `UUID`

### Output format

```json
{
  "statusCode": 200,
  "message": "Get exam by id",
  "data": {
    "examUuid": "uuid",
    "examName": "string",
    "gradeId": 12,
    "examType": "QUIZ | HOMEWORK | MOCK_TEST | OFFICIAL_TEST",
    "startTime": "2026-05-17T10:00:00Z | null",
    "endTime": "2026-05-17T11:00:00Z | null",
    "durationMinutes": 45,
    "totalScore": 10.0,
    "numberOfAttempt": 1,
    "status": "DRAFT | PUBLISHED | CLOSED | ARCHIVED",
    "createdByUserUuid": "uuid",
    "tfCorrect1Pct": 10,
    "tfCorrect2Pct": 25,
    "tfCorrect3Pct": 50,
    "tfCorrect4Pct": 100,
    "questionSummary": {
      "mcqCount": 10,
      "tfqCount": 4,
      "saqCount": 2
    },
    "questionSections": [],
    "createdAt": "2026-05-17T10:00:00Z",
    "updatedAt": "2026-05-17T10:00:00Z",
    "createdBy": "string | null",
    "updatedBy": "string | null"
  }
}
```

### Exception có thể trả về

#### `400 Bad Request`

- `Exam not found with id: {examUuid}`

#### `403 Forbidden`

- khi JWT không đủ quyền truy cập

#### `500 Internal Server Error`

- lỗi không mong muốn từ backend

---

## 5.3. Lấy danh sách đề

### Đường dẫn

`GET /api/v1/exams`

### Mô tả luồng

Nhận các tham số filter -> dựng điều kiện truy vấn động theo `gradeId`, `name`, `type`, `status` -> chạy query phân trang -> build chi tiết từng đề trong trang hiện tại -> trả kết quả dạng page

### Input format

#### Query params

- `gradeId`: `Long`, không bắt buộc
- `name`: `String`, không bắt buộc
- `type`: `String`, không bắt buộc
- `status`: `String`, không bắt buộc
- `page`: `int`, không bắt buộc
- `size`: `int`, không bắt buộc
- `sort`: `String`, không bắt buộc

#### Ví dụ

`GET /api/v1/exams?gradeId=12&type=QUIZ&status=PUBLISHED&page=0&size=20&sort=createdAt,desc`

### Output format

Do backend đang trả `Page<ResExamDTO>`, dữ liệu trong `data` sẽ là object phân trang của Spring, ví dụ:

```json
{
  "statusCode": 200,
  "message": "Get exams",
  "data": {
    "content": [
      {
        "examUuid": "uuid",
        "examName": "string",
        "gradeId": 12,
        "examType": "QUIZ",
        "startTime": "2026-05-17T10:00:00Z",
        "endTime": "2026-05-17T11:00:00Z",
        "durationMinutes": 45,
        "totalScore": 10.0,
        "numberOfAttempt": 1,
        "status": "PUBLISHED",
        "createdByUserUuid": "uuid",
        "tfCorrect1Pct": 10,
        "tfCorrect2Pct": 25,
        "tfCorrect3Pct": 50,
        "tfCorrect4Pct": 100,
        "questionSummary": {
          "mcqCount": 10,
          "tfqCount": 4,
          "saqCount": 2
        },
        "questionSections": [],
        "createdAt": "2026-05-17T10:00:00Z",
        "updatedAt": "2026-05-17T10:00:00Z",
        "createdBy": "string | null",
        "updatedBy": "string | null"
      }
    ],
    "pageable": {},
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "sort": {},
    "first": true,
    "numberOfElements": 1,
    "empty": false
  }
}
```

### Exception có thể trả về

#### `400 Bad Request`

- `Invalid exam type: {type}`
- `Invalid exam status: {status}`

#### `403 Forbidden`

- khi JWT không đủ quyền truy cập

#### `500 Internal Server Error`

- lỗi không mong muốn từ backend

---

## 5.4. Cập nhật đề

### Đường dẫn

`PUT /api/v1/exams/{examUuid}`

### Mô tả luồng

Nhận `examUuid` và payload mới -> kiểm tra đề có tồn tại không -> validate toàn bộ dữ liệu giống API tạo đề -> cập nhật bản ghi `Exam` -> xóa toàn bộ `ExamQuestion` cũ -> xóa toàn bộ `ExamQuestionGroupItem` cũ -> xóa toàn bộ `ExamQuestionGroup` cũ -> tạo lại cấu trúc đề từ payload mới -> build response -> trả kết quả

### Input format

#### Path variable

- `examUuid`: `UUID`

#### Body

Cấu trúc body giống `POST /api/v1/exams`

### Output format

```json
{
  "statusCode": 200,
  "message": "Update exam",
  "data": {
    "examUuid": "uuid",
    "examName": "string",
    "gradeId": 12,
    "examType": "QUIZ | HOMEWORK | MOCK_TEST | OFFICIAL_TEST",
    "startTime": "2026-05-17T10:00:00Z | null",
    "endTime": "2026-05-17T11:00:00Z | null",
    "durationMinutes": 45,
    "totalScore": 10.0,
    "numberOfAttempt": 1,
    "status": "DRAFT | PUBLISHED | CLOSED | ARCHIVED",
    "createdByUserUuid": "uuid",
    "tfCorrect1Pct": 10,
    "tfCorrect2Pct": 25,
    "tfCorrect3Pct": 50,
    "tfCorrect4Pct": 100,
    "questionSummary": {
      "mcqCount": 10,
      "tfqCount": 4,
      "saqCount": 2
    },
    "questionSections": [],
    "createdAt": "2026-05-17T10:00:00Z",
    "updatedAt": "2026-05-17T10:05:00Z",
    "createdBy": "string | null",
    "updatedBy": "string | null"
  }
}
```

### Exception có thể trả về

#### `400 Bad Request`

- `Exam not found with id: {examUuid}`
- toàn bộ lỗi validation giống API tạo đề

#### `403 Forbidden`

- khi JWT không đủ quyền truy cập

#### `500 Internal Server Error`

- lỗi không mong muốn từ backend

---

## 5.5. Cập nhật trạng thái đề

### Đường dẫn

`PATCH /api/v1/exams/{examUuid}/status`

### Mô tả luồng

Nhận `examUuid` và `status` mới -> kiểm tra đề có tồn tại không -> cập nhật trạng thái của đề -> lưu xuống database -> build lại response chi tiết -> trả kết quả

### Input format

#### Path variable

- `examUuid`: `UUID`

#### Body

```json
{
  "status": "PUBLISHED"
}
```

### Output format

```json
{
  "statusCode": 200,
  "message": "Update exam status",
  "data": {
    "examUuid": "uuid",
    "examName": "string",
    "gradeId": 12,
    "examType": "QUIZ | HOMEWORK | MOCK_TEST | OFFICIAL_TEST",
    "startTime": "2026-05-17T10:00:00Z | null",
    "endTime": "2026-05-17T11:00:00Z | null",
    "durationMinutes": 45,
    "totalScore": 10.0,
    "numberOfAttempt": 1,
    "status": "PUBLISHED",
    "createdByUserUuid": "uuid",
    "tfCorrect1Pct": 10,
    "tfCorrect2Pct": 25,
    "tfCorrect3Pct": 50,
    "tfCorrect4Pct": 100,
    "questionSummary": {
      "mcqCount": 10,
      "tfqCount": 4,
      "saqCount": 2
    },
    "questionSections": [],
    "createdAt": "2026-05-17T10:00:00Z",
    "updatedAt": "2026-05-17T10:10:00Z",
    "createdBy": "string | null",
    "updatedBy": "string | null"
  }
}
```

### Exception có thể trả về

#### `400 Bad Request`

- `Exam not found with id: {examUuid}`
- `Status is required`

#### `403 Forbidden`

- khi JWT không đủ quyền truy cập

#### `500 Internal Server Error`

- lỗi không mong muốn từ backend

---

## 6. Luồng liên quan module khác

### 6.1. Liên quan `Question Module`

Khi tạo hoặc cập nhật đề, backend có truy vấn sang dữ liệu câu hỏi để:

- kiểm tra `questionUuid` có tồn tại không
- kiểm tra `sectionType` có khớp `questionType` không
- kiểm tra topic của câu hỏi có khớp group topic không
- lấy `questionContent`, `questionTopic`, `questionType` để build response chi tiết

### 6.2. Liên quan `Exam Attempt Module`

`Exam Module` không random câu hỏi ngay khi tạo đề.

Thay vào đó:

- `ExamQuestion` là câu hỏi lẻ cố định
- `ExamQuestionGroup` là pool câu hỏi
- `pickQuestionCount` sẽ được dùng ở `Exam Attempt Module`
- khi học sinh bắt đầu `attempt`, hệ thống mới random câu trong từng group

### 6.3. Liên quan `Question Group Module`

`Exam Module` hiện không còn coi group là dữ liệu chỉ sống bên trong đề.

Thay vào đó:

- có thể tham chiếu group có sẵn bằng `questionGroupUuid`
- có thể tạo mới group bằng `newQuestionGroup`
- khi gắn vào đề, backend sẽ snapshot sang `ExamQuestionGroup` và `ExamQuestionGroupItem`

Vì vậy, mọi thay đổi trong `Exam Module` sẽ ảnh hưởng trực tiếp đến:

- số lượng câu hỏi mỗi loại mà học sinh nhìn thấy
- cấu trúc group khi random đề
- cách chấm điểm từng câu lấy từ group

---

## 7. Ghi chú cho frontend

### 7.1. Về `gradeId`

- `gradeId` là `Long`
- không dùng `UUID`

### 7.2. Về `questionSummary`

`questionSummary` là tổng số câu thực tế theo từng loại mà frontend nên hiển thị ở mức tổng quan.

Riêng với câu hỏi trong group:

- backend tính theo `pickQuestionCount`
- không tính theo toàn bộ pool `questionCount`

### 7.3. Về `questionSections`

Frontend nên render theo từng block:

- `MCQ`
- `TFQ`
- `SAQ`

Trong mỗi block:

- `standaloneQuestions` là câu hỏi cố định
- `groups` là nhóm câu hỏi

### 7.4. Về group câu hỏi

Một đề có thể:

- chỉ có câu hỏi lẻ
- chỉ có group
- hoặc có cả hai

Một loại như `MCQ` có thể có nhiều group khác nhau, ví dụ:

- `Tích phân`
- `Nguyên hàm`

---

## 8. Gợi ý kiểm thử frontend

### 8.1. Tạo đề cơ bản

- tạo đề chỉ có câu hỏi lẻ
- tạo đề chỉ có group
- tạo đề có cả câu hỏi lẻ và group

### 8.2. Kiểm thử validation

- `endTime` nhỏ hơn `startTime`
- `questionOrder` bị trùng
- `displayOrder` bị trùng
- `pickQuestionCount > questionCount`
- question trong group sai `questionType`
- question trong group sai `questionTopic`

### 8.3. Kiểm thử filter

- filter theo `gradeId`
- filter theo `type`
- filter theo `status`
- filter theo `name`

### 8.4. Kiểm thử response structure

- kiểm tra `questionSummary`
- kiểm tra `questionSections`
- kiểm tra `standaloneQuestions`
- kiểm tra `groups`
- kiểm tra `poolQuestionCount` và `pickQuestionCount`
