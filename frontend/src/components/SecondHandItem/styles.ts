import styled from 'styled-components';
import Button, { ButtonProps } from '../Button';

interface SecondHandItemProps {
  imageURI: string;
}

interface StatusLabelProps extends ButtonProps {
  round: boolean;
  status: string;
}

export const ItemContainer = styled.div`
  margin: 16px;
  display: flex;
  border-bottom: 1px solid rgba(179, 179, 179, 0.39);
`;

export const ItemImage = styled.div<SecondHandItemProps>`
  width: 120px;
  height: 120px;
  background-image: url(${(props) => props.imageURI});
  background-size: contain;
  margin-right: 16px;
  margin-bottom: 16px;
`;

export const ItemInfo = styled.div`
  display: flex;
  flex-direction: column;
  width: calc(100% - 136px);
`;

export const ItemContents = styled.div`
  display: flex;
  justify-content: space-between;
`;

export const Content = styled.div`
  display: block;
  width: 220px;
  height: 100px;
`;

export const ColumnTop = styled.div`
  margin-bottom: 4px;
  ${({ theme }) => theme.typo.subhead}
  ${({ theme }) => theme.color.neutralText}
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
`;
export const ColumnMid = styled.div`
  margin-bottom: 4px;
  ${({ theme }) => theme.typo.footnote}
  ${({ theme }) => theme.color.neutralTextWeak}
`;
export const ColumnBot = styled.div`
  display: flex;
  align-items: center;
`;

export const StatusLabel = styled(Button)<StatusLabelProps>`
  background-color: ${(props) =>
    props.status === '예약중'
      ? '#00C7BE'
      : props.status === '판매완료'
      ? '#767680'
      : ''};
  color: white;
  padding: 10px 8px;
  height: 22px;
  ${({ theme }) => theme.typo.caption1}
  margin-right: 8px;
  cursor: default;
`;

export const Price = styled.span`
  ${({ theme }) => theme.typo.headline}
`;

export const ItemIssue = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-end;
`;

export const Message = styled.div`
  display: flex;
  ${({ theme }) => theme.typo.footnote}
  align-items: center;

  span {
    margin-left: 3px;
  }
`;

export const heart = styled.div`
  display: flex;
  ${({ theme }) => theme.typo.footnote}
  align-items: center;
  margin-left: 5px;

  span {
    margin-left: 3px;
  }
`;
