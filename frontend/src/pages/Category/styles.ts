import styled from 'styled-components';

export const Categories = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(80px, 1fr));
  grid-gap: 36px;
  justify-items: center;
  padding: 40px;
  margin-top: 50px;
`;

export const Category = styled.div`
  width: 80px;
  height: 68px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;

  span {
    font-weight: 400;
    font-size: 13px;
    line-height: 20px;
    margin-top: 4px;
    ${({ theme }) => theme.color.neutralText}
  }
`;
