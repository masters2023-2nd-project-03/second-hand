import { useContext, useEffect, useState } from 'react';

import * as S from './styles';

import {
  postSalesItemContext,
  PostObjectType,
} from '../../context/SalesItem/useContext';

const COMMENT_PLACEHOLDER =
  '역삼1동에 올릴 게시물 내용을 작성해주세요. (판매듬지 물품은 게시가 제한될 수 있어요.)';

const COMMENT_MAX_LENGTH = 500;

// TODO : 엔터치면 무한이 늘어나는 텍스트에어리아 높이 제한 주기
// https://bydawn25.tistory.com/44

const UploadComment = () => {
  const [textareaValue, setTextareaValue] = useState('');
  const [textareaHeight, setTextareaHeight] = useState(110);
  const { setPostObject } = useContext(postSalesItemContext);

  const handleTextareaChange = (
    event: React.ChangeEvent<HTMLTextAreaElement>,
  ) => {
    const inputValue = event.target.value;
    // const rowCount = inputValue.split(/\r\n|\r|\n/).length;
    const newHeight = event.target.scrollHeight;

    setTextareaValue(inputValue);

    if (textareaValue.length > inputValue.length) {
      setTextareaHeight(newHeight - 22);
    } else {
      setTextareaHeight(newHeight);
    }
  };

  useEffect(() => {
    setPostObject((prevPostObject: PostObjectType) => ({
      ...prevPostObject,
      content: textareaValue,
    }));
  }, [textareaValue]);

  return (
    <>
      <S.CommentContainer>
        <S.testDiv>
          <S.CommentTextarea
            placeholder={COMMENT_PLACEHOLDER}
            value={textareaValue}
            onChange={handleTextareaChange}
            maxLength={COMMENT_MAX_LENGTH}
            height={textareaHeight}
          />
        </S.testDiv>
        <S.CommentLengthNotify>
          {textareaValue.length} / {COMMENT_MAX_LENGTH}
        </S.CommentLengthNotify>
      </S.CommentContainer>
    </>
  );
};

export default UploadComment;
