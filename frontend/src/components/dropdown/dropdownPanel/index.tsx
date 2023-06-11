import * as S from './styles';

interface DropdownPanelProps {
  option: string;
  onClick: (option: string) => void;
  isLastPanel?: boolean;
}

const DropdownPanel: React.FC<DropdownPanelProps> = ({
  option,
  onClick,
  isLastPanel,
}) => {
  return (
    <S.dropdownPanel onClick={() => onClick(option)} isLastPanel={isLastPanel}>
      <S.optionTitle>{option}</S.optionTitle>
    </S.dropdownPanel>
  );
};

export default DropdownPanel;
