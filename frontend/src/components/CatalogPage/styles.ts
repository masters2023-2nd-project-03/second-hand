import styled from 'styled-components';

export const Catalog = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(80px, 1fr));
  grid-gap: 36px;
  padding: 40px;
`;

export const Item = styled.div`
  width: 80px;
  height: 68px;
  background-color: skyblue;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;

  span {
    font-weight: 400;
    font-size: 13px;
    line-height: 20px;
    margin-top: 4px;
    color: #3c3c43;
  }
`;
