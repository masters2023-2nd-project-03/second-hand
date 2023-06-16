import styled from 'styled-components';

export const Catalog = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  grid-gap: 10px;
`;

export const Item = styled.div`
  background-color: lightgray;
  padding: 10px;
`;
