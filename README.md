# 우테코 출석 모니터링

출석하지 않는 크루들의 이름을 확인할 수 있는 어플리케이션 입니다.

## 설치

1. API 키 설정 - https://developers.google.com/sheets/api/quickstart/java 의 `환경 설정하기`안에 있는 내용 진행해서 `credientials.json` 파일
   획득
2. `credentials.json`을 `src/main/resources`에 넣기
3. 출석 테스트 시트(https://docs.google.com/spreadsheets/d/1pjZUxFqEvk-vdfEeX8w58F9llRO9-RZbFZFWQyFvr8w/edit?usp=sharing)를
   참고하면서 `src/main/resource/google-api.yml` 파일 설정 (sheetId, sheetRange)

```
spread-sheet-id: [docs.google.com/spreadsheets/d/[이부분 넣기]]
spread-sheet-range: [출석부 sheet range 넣기]
```

4. `src/main/resources/data.sql`을 생성해서 모니터링 할 크루 등록

``` sql
INSERT INTO attendance (name, campus_name) VALUES ('포케', '잠실');
INSERT INTO attendance (name, campus_name) VALUES ('짱수', '잠실');
INSERT INTO attendance (name, campus_name) VALUES ('아루', '잠실');
INSERT INTO attendance (name, campus_name) VALUES ('크루', '선릉');
```

5. 실행하면 콘솔 창에 아래와 같이 token url이 나오는데 해당 url에 접속해서 토큰 획득

```bash
Please open the following address in your browser:
  https://accounts.google.com/o/oauth2/auth?access_type=offline&client_id=`클라이언트id`.apps.googleusercontent.com&redirect_uri=http://localhost:8888/Callback&response_type=code&scope=https://www.googleapis.com/auth/spreadsheets.readonly
```

6. 획득 이후 http://localhost:8080 접속하면 끝!

## TODO

- [ ] 출석 확인이 되지 않는 크루에게 slack dm 전송하기

## 도움을 주신 분들

### 아이디어 제시

- [우테코 6기 BE 짱수](https://github.com/zangsu)
- [우테코 6기 BE 도비](https://github.com/Dobby-Kim)

### 프로젝트 기여

- [우테코 6기 BE 아루](https://github.com/donghoony)