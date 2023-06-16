import * as S from './styles';

interface DropdownPanelProps {
  option: string;
  onClick: (option: string) => void;
  isLastPanel?: boolean;
}

const DropdownPanel = ({
  option,
  onClick,
  isLastPanel,
}: DropdownPanelProps) => {
  return (
    <S.dropdownPanel onClick={() => onClick(option)} isLastPanel={isLastPanel}>
      <S.optionTitle isLastPanel={isLastPanel}>{option}</S.optionTitle>
    </S.dropdownPanel>
  );
};

export default DropdownPanel;
