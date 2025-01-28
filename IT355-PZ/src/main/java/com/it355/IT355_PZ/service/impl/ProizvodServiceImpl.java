    package com.it355.IT355_PZ.service.impl;

    import com.it355.IT355_PZ.entity.Proizvod;
    import com.it355.IT355_PZ.repository.ProizvodRepository;
    import com.it355.IT355_PZ.service.ProizvodService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;
    @Service
    @RequiredArgsConstructor
    public class ProizvodServiceImpl implements ProizvodService {

        private final ProizvodRepository proizvodRepository;
        @Override
        public List<Proizvod> findAll() {
            return proizvodRepository.findAll();
        }

        @Override
        public Optional<Proizvod> findById(Integer proizvodId) {
            return proizvodRepository.findById(proizvodId);
        }

        @Override
        public Proizvod save(Proizvod proizvod) {
            return proizvodRepository.save(proizvod);
        }

        @Override
        public Proizvod update(Proizvod proizvod) {
            return proizvodRepository.save(proizvod);
        }

        @Override
        public void deleteById(Integer proizvodId) {
            proizvodRepository.deleteById(proizvodId);
        }
    }
