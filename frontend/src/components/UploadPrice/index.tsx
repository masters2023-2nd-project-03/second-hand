import { useState, useContext, useEffect } from 'react';

import {
  postSalesItemContext,
  PostObjectType,
} from '../../context/SalesItem/useContext';
import * as S from './styles';
import formatNumber from '../../utils/formatNumber';

const UploadPrice = () => {
  const [inputPrice, setInputPrice] = useState<string>('');
  const { postObject, setPostObject } = useContext(postSalesItemContext);

  const formattedPrice = formatNumber(parseInt(inputPrice));

  const handlePriceInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value;
    setInputPrice(inputValue);
  };

  useEffect(() => {
    setPostObject((prevPostObject: PostObjectType) => ({
      ...prevPostObject,
      price: Number(inputPrice),
    }));
  }, [inputPrice]);

  return (
    <>
      <S.UploadPrice>
        <S.Title>
          <S.InputPrice
            placeholder="₩ 가격 (선택사항)"
            type="number"
            onChange={handlePriceInput}
          />
          {inputPrice !== '' && (
            <S.FormattedPrice>₩ {formattedPrice} 원</S.FormattedPrice>
          )}
        </S.Title>
      </S.UploadPrice>
    </>
  );
};

export default UploadPrice;
