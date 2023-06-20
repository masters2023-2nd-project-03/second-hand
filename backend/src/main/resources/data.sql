INSERT INTO category (title, category_img_url)
VALUES
    ('디지털기기', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_01.PNG'),
    ('생활가전', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_02.PNG'),
    ('가구/인테리어', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_03.PNG'),
    ('생활/주방', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_04.PNG'),
    ('유아동', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_05.PNG'),
    ('유아도서', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_06.PNG'),
    ('여성의류', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_07.PNG'),
    ('여성잡화', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_08.PNG'),
    ('남성패션/잡화', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_09.PNG'),
    ('뷰티/미용', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_10.PNG'),
    ('스포츠/레저', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_11.PNG'),
    ('취미/게임/음반', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_12.PNG'),
    ('중고차', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_13.PNG'),
    ('티켓/교환권', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_14.PNG'),
    ('가공식품', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_15.PNG'),
    ('반려동물용품', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_16.PNG'),
    ('식물', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_17.PNG'),
    ('기타 중고물품', 'https://second-hand-s3.s3.ap-northeast-2.amazonaws.com/category_img/category_18.PNG');

INSERT INTO location (location_details, location_shortening)
VALUES
    ('서울특별시 강남구 개포1동', '개포1동'),
    ('서울특별시 강남구 개포2동', '개포2동'),
    ('서울특별시 강남구 개포3동', '개포3동'),
    ('서울특별시 강남구 개포4동', '개포4동'),
    ('서울특별시 강남구 논현1동', '논현1동'),
    ('서울특별시 강남구 논현2동 ', '논현2동'),
    ('서울특별시 강남구 대치1동', '대치1동'),
    ('서울특별시 강남구 대치2동', '대치2동'),
    ('서울특별시 강남구 대치4동', '대치4동'),
    ('서울특별시 강남구 도곡1동', '도곡1동'),
    ('서울특별시 강남구 도곡2동', '도곡2동'),
    ('서울특별시 강남구 삼성1동', '삼성1동'),
    ('서울특별시 강남구 삼성2동', '삼성2동'),
    ('서울특별시 강남구 세곡동', '세곡동'),
    ('서울특별시 강남구 수서동', '수서동'),
    ('서울특별시 강남구 신사동', '신사동'),
    ('서울특별시 강남구 압구정동', '압구정동'),
    ('서울특별시 강남구 역삼1동', '역삼1동'),
    ('서울특별시 강남구 역삼2동', '역삼2동'),
    ('서울특별시 강남구 일원1동', '일원1동'),
    ('서울특별시 강남구 일원본동', '일원본동'),
    ('서울특별시 강남구 청담동', '청담동');

INSERT INTO member (nickname, profile_url, oauth_id)
VALUES
    ('만주', 'https://avatars.githubusercontent.com/u/20828490?s=40&v=4', 'JeonHyoChang');