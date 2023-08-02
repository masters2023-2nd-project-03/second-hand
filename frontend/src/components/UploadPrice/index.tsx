import { useState, useContext, useEffect } from 'react';

import * as S from './styles';

import {
  postSalesItemContext,
  PostObjectType,
} from '../../context/SalesItem/useContext';
import { formatString } from '../../utils/formatNumber';

// TODO : 가격 필터링 더 멋지게 바꾸기 ex) 백만 단위 천만 단위 등

const UploadPrice = () => {
  const { setPostObject } = useContext(postSalesItemContext);
  const [inputPrice, setInputPrice] = useState<string>('');
  const [formattedPrice, setFormattedPrice] = useState<string>('');

  const handlePriceInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    let inputValue = event.currentTarget.value.replace(/[^0-9]/g, '');
    inputValue = inputValue.slice(0, 10);

    const formattedValue = formatString(inputValue);
    setInputPrice(inputValue);
    setFormattedPrice(formatString(formattedValue));
  };

  useEffect(() => {
    setPostObject((prevPostObject: PostObjectType) => ({
      ...prevPostObject,
      price: inputPrice !== '' ? Number(inputPrice) : null,
    }));
  }, [inputPrice]);

  return (
    <>
      <S.UploadPrice>
        <S.Title>
          <S.InputPrice
            placeholder="₩ 가격 (선택사항)"
            type="text"
            pattern="^[0-9]*$"
            onChange={handlePriceInput}
            value={formattedPrice}
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
