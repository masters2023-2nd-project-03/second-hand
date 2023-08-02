import styled from 'styled-components';

export const UploadPrice = styled.div`
  padding: 15px 0;
  border-bottom: 1px solid rgba(179, 179, 179, 0.39);
`;

export const Title = styled.div`
  width: 100%;
`;

export const InputPrice = styled.input`
  ${({ theme }) => theme.color.subhead}
  border: none;
  outline: none;
  background: transparent;
  box-shadow: none;
  resize: none;
  width: 100%;
  overflow: hidden;
`;

export const FormattedPrice = styled.div`
  ${({ theme }) => theme.color.headline}
`;
