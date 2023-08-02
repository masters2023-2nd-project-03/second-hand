import styled from 'styled-components';

interface textareaHeightType {
  height: number;
}

export const CommentContainer = styled.div`
  padding: 15px 0;
  border-bottom: 1px solid rgba(179, 179, 179, 0.39);
`;

export const CommentTextarea = styled.textarea<textareaHeightType>`
  width: 100%;
  border: none;
  resize: none;
  height: ${({ height }) => (height > 0 ? `${height}px` : 'auto')};
  min-height: 110px;

  font-size: 15px;
  line-height: 22px;

  :focus {
    outline: none;
  }
`;

export const CommentLengthNotify = styled.span`
  display: flex;
  justify-content: flex-end;
  opacity: 0.5;
`;

export const testDiv = styled.div`
  max-height: 500px;
`;
