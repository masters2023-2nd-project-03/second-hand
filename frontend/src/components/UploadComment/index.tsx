import { useContext, useState } from 'react';

import {
  postSalesItemContext,
  PostObjectType,
} from '../../context/SalesItem/useContext';
import * as S from './styles';

const COMMENT_PLACEHOLDER =
  '역삼1동에 올릴 게시물 내용을 작성해주세요. (판매듬지 물품은 게시가 제한될 수 있어요.)';

const COMMENT_MAX_LENGTH = 500;

const UploadComment = () => {
  const [textareaValue, setTextareaValue] = useState('');
  const [textareaHeight, setTextareaHeight] = useState(110);
  const { setPostObject } = useContext(postSalesItemContext);

  const handleTextareaChange = (
    event: React.ChangeEvent<HTMLTextAreaElement>,
  ) => {
    const inputValue = event.target.value;
    setTextareaValue(inputValue);

    const newHeight = event.target.scrollHeight;

    if (textareaValue.length > inputValue.length) {
      setTextareaHeight(newHeight - 22);
    } else {
      setTextareaHeight(newHeight);
    }
  };

  return (
    <>
      <S.CommentContainer>
        <S.CommentTextarea
          placeholder={COMMENT_PLACEHOLDER}
          value={textareaValue}
          onChange={handleTextareaChange}
          maxLength={COMMENT_MAX_LENGTH}
          height={textareaHeight}
        />
        <S.CommentLengthNotify>
          {textareaValue.length} / {COMMENT_MAX_LENGTH}
        </S.CommentLengthNotify>
      </S.CommentContainer>
    </>
  );
};

export default UploadComment;
